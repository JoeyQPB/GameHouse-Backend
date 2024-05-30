package com.ms.gamehouseapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class GamehouseApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamehouseApiGatewayApplication.class, args);
	}

}
