package com.irecon.innovation.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
	
	@Autowired
	private EmailService emailService;
	
	@KafkaListener(topics = "user-topic" , groupId = "email-group")
	public void consume(String email) {
		System.out.println("Email Address" +email);
		 email = email.trim();
		emailService.sendEmail(email, "Registration Successfully", "Welcome your account has been created successfully! For More Info Contact Admin");
		
	}

}
