package com.spring.rabbit.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.AnonymousQueue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 
 * @author Shekar @ pasemsekhar@gmail.com
 *
 */
@Configuration
public class RabbitMqConfig {

	@Bean
	public ConnectionFactory connectionFactory() {

		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost", 5672);
		connectionFactory.setUsername("wensa");
		connectionFactory.setPassword("P@ss123");
		return connectionFactory;
	}

	@Bean
	public Queue queue72() {
		return new AnonymousQueue();
	}

	@Bean
	public Queue queue73() {
		return new AnonymousQueue();
	}

	@Bean
	public Queue queuefan1() {
		return new AnonymousQueue();
	}

	@Bean
	public Queue queuefan2() {
		return new AnonymousQueue();
	}

	@Bean
	public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
		return new RabbitAdmin(connectionFactory);
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		return new RabbitTemplate(connectionFactory());
	}

	@Bean
	DirectExchange exchange72() {
		return new DirectExchange("exchange72");
	}

	@Bean
	FanoutExchange exchangefan() {
		return new FanoutExchange("exchangefan");
	}

	@Bean
	Binding queue72Binding(Queue queue72, DirectExchange exchange72) {
		return BindingBuilder.bind(queue72).to(exchange72).with("queue72");
	}

	@Bean
	Binding queue73Binding(Queue queue73, DirectExchange exchange72) {
		return BindingBuilder.bind(queue73).to(exchange72).with("queue73");
	}

	@Bean
	Binding queueFan1Binding(Queue queuefan1, FanoutExchange exchangefan) {
		return BindingBuilder.bind(queuefan1).to(exchangefan);
	}

	@Bean
	Binding queueFan2Binding(Queue queuefan2, FanoutExchange exchangefan) {
		return BindingBuilder.bind(queuefan2).to(exchangefan);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new JsonMessageConverter();
	}

	@Bean
	public RabbitTemplate sample72() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		template.setRoutingKey("queue73");
		template.setExchange(exchange72().getName());
		template.setMessageConverter(jsonMessageConverter());
		return template;
	}

	@Bean
	public RabbitTemplate samplefanTMT() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		// template.setRoutingKey("queue73");
		template.setExchange(exchangefan().getName());
		template.setMessageConverter(jsonMessageConverter());
		return template;
	}
	@Bean
	public RabbitTemplate samplefanDynaTMT() {
		RabbitTemplate template = new RabbitTemplate(connectionFactory());
		template.setMessageConverter(jsonMessageConverter());
		return template;
	}
	@Bean
	public SimpleMessageListenerContainer sampleListenerContainer() {
		SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
		listenerContainer.setConnectionFactory(connectionFactory());
		listenerContainer.setQueues(queue73());
		listenerContainer.setMessageConverter(jsonMessageConverter());
		listenerContainer.setMessageListener(new SampleMessageListener());
		listenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
		return listenerContainer;
	}

	@Bean
	public SimpleMessageListenerContainer samplefan1ListenerContainer() {
		SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
		listenerContainer.setConnectionFactory(connectionFactory());
		listenerContainer.setQueues(queuefan1());
		listenerContainer.setMessageConverter(jsonMessageConverter());
		listenerContainer.setMessageListener(new FanListener1());
		listenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
		return listenerContainer;
	}

	@Bean
	public SimpleMessageListenerContainer samplefan2ListenerContainer() {
		SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
		listenerContainer.setConnectionFactory(connectionFactory());
		listenerContainer.setQueues(queuefan2());
		listenerContainer.setMessageConverter(jsonMessageConverter());
		listenerContainer.setMessageListener(new FanListener2());
		listenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
		return listenerContainer;
	}
}
