package com.irecon.innovation.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemple;
	
	private static final String TOPIC= "user-topic";
	
	public void sendUserCreateEvent(String email) {
		kafkaTemple.send(TOPIC,email);
	}
	
	

}
