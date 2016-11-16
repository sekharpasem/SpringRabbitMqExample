package com.spring.rabbit.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
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
public class FanSenderDyna {

	@Autowired
	RabbitTemplate samplefanDynaTMT;
	@Autowired
	RabbitAdmin rabbitAdmin;

	public FanSenderDyna() {
		System.out.println("creating instance of FanSenderDyna");
	}

	//@Scheduled(fixedRate = 5000)
	public void send() {
		System.out.println("instace " + samplefanDynaTMT);
		try {
			String message = "HelloWorld";
			System.out.println("Sender");
			Exchange exchange = new FanoutExchange("samplefanout");
			rabbitAdmin.declareExchange(exchange);
			samplefanDynaTMT.setExchange("samplefanout");
			samplefanDynaTMT.convertAndSend(message);
			System.out.println("Sent message");
			send2();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void send2() {
		Exchange exchange = new FanoutExchange("samplefanout1");
		rabbitAdmin.declareExchange(exchange);
		samplefanDynaTMT.setExchange("samplefanout1");
		samplefanDynaTMT.convertAndSend("fanout-ni yabba");
	}
}
