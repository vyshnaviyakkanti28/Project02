package com.revature.projectweb02.services;

import java.time.Instant;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.revature.projectweb02.dto.RegisterRequest;
import com.revature.projectweb02.model.NotificationEmail;
import com.revature.projectweb02.model.User;
import com.revature.projectweb02.model.VerificationToken;
import com.revature.projectweb02.repository.UserRepository;
import com.revature.projectweb02.repository.VerificationTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class AuthorizeService {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	@Autowired
	private MailService mailService;
	
	@Transactional
	public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please do the activation process",user.getEmail(),"Thankyou for Signing up into our app. Hope you have a great experience over here. Please click on the below url to activate your account:"+"http://localhost:8080/api/authorization/accountVerification/"+token));
       
    }
	

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }
	
	public void verifyAccount(String token) {
		Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
		verificationToken.orElseThrow(()-> new SpringRedditException("Invalid Token"));
		fetchUserAndEnable(verificationToken.get());
		
	}

	@Transactional
	private void fetchUserAndEnable(VerificationToken verificationToken) {
		// TODO Auto-generated method stub
		String username=verificationToken.getUser().getUsername();
		User user=userRepository.findByUsername(username).orElseThrow(()->new SpringRedditException("User not found with name - "+ username));
		user.setEnabled(true);
	}
	
	

}
