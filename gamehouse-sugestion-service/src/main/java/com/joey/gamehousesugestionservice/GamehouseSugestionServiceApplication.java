package com.joey.gamehousesugestionservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class GamehouseSugestionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamehouseSugestionServiceApplication.class, args);
	}

}
