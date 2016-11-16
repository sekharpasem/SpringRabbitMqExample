package com.spring.rabbit.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * 
 * @author Shekar @ pasemsekhar@gmail.com
 *
 */
@Component
public class FanSender {

	@Autowired
	RabbitTemplate samplefanTMT;

	public FanSender() {
		System.out.println("creating instance of FanSender");
	}

	//@Scheduled(fixedRate = 5000)
	public void send() {
		System.out.println("instace "+samplefanTMT);
		try {
			String message = "HelloWorld";
			System.out.println("Sender");
			samplefanTMT.convertAndSend(message);
			System.out.println("Sent message");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
