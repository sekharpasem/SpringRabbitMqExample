package com.spring.rabbit.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.stereotype.Component;

/***
 * 
 * 
 * @author Shekar
 *
 */
@Component
public class SampleMessageListener implements MessageListener {

	/***
	 * logger
	 */

	public SampleMessageListener() {
		System.out.println("SampleMessageListener instance");
	}

	public void onMessage(Message message) {
		System.out.println("Listened " + message);
		try {
			JsonMessageConverter jmc = new JsonMessageConverter();
			String messa = (String) jmc.fromMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
