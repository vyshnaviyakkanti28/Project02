package com.revature.projectweb02.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.revature.projectweb02.exception.SpringRedditException;
import com.revature.projectweb02.model.NotificationEmail;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class MailService {
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private MailContent mailContent;
	public void sendMail(NotificationEmail notifyEmail) {
		 MimeMessagePreparator messagePreparator = mimeMessage -> {
	            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
	            messageHelper.setFrom("disseminator@email.com");
	            messageHelper.setTo(notifyEmail.getRecipient());
	            messageHelper.setSubject(notifyEmail.getSubject());
	            messageHelper.setText(notifyEmail.getBody());
	        };
	        try {
	            mailSender.send(messagePreparator);
	        } catch (MailException e) {
	            throw new SpringRedditException("Exception occurred when sending mail to " + notifyEmail.getRecipient(), e);
	        }
	    }
	}


