package com.spring.rabbit.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/**
 * 
 * @author Shekar @ pasemsekhar@gmail.com
 *
 */
public class Main {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class,RabbitMqConfig.class);
	}

}
