package com.spring.rabbit.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 
 * @author Shekar
 *
 */
@EnableScheduling
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.spring.rabbit.*")
public class Config {

}
