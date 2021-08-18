package com.revature.projectweb02.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContent {
	@Autowired
	private TemplateEngine templateEngine;
	String build(String message) {
		Context context=new Context();
		context.setVariable("message", message);
		return templateEngine.process("Mail",context);
	}
}
