package com.spring.rabbit.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.stereotype.Component;
/**
 * 
 * @author Shekar @ pasemsekhar@gmail.com
 *
 */
@Component
public class FanListener2 implements MessageListener {

	/***
	 * logger
	 */

	public FanListener2() {
		System.out.println("FanListener2 instance");
	}

	public void onMessage(Message message) {
		System.out.println("FanListener2 listened " + message);
		try {
			JsonMessageConverter jmc = new JsonMessageConverter();
			String messa = (String) jmc.fromMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

