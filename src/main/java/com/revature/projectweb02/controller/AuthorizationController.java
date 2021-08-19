package com.revature.projectweb02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.projectweb02.dto.RegisterRequest;
import com.revature.projectweb02.services.AuthorizeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/authorization")
@AllArgsConstructor
public class AuthorizationController {
	
	@Autowired
	private AuthorizeService authService;
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
		authService.signup(registerRequest);
		return new ResponseEntity<>("User Registration Successful",HttpStatus.OK);
		}
	
	@GetMapping("accountVerification/{token}")
	public ResponseEntity<String> verifyAccount(@PathVariable String token){
		authService.verifyAccount(token);
		return new ResponseEntity<>("Account Activated Successfully", HttpStatus.OK);
	}
	}


