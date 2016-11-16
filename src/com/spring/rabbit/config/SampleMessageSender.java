package com.spring.rabbit.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/***
 * send rabbitMQ broker
 * 
 * @author Shekar
 *
 */
@Component
public class SampleMessageSender {

	@Autowired
	RabbitTemplate sample72;

	public SampleMessageSender() {
		System.out.println("creating instance of SampleMessageSender");
	}

	@Scheduled(fixedRate = 5000)
	public void send() {
		try {
			String message = "HelloWorld";
			System.out.println("Sender");
			sample72.convertAndSend(message);
			System.out.println("Sent message");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}