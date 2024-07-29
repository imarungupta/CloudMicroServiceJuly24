package com.sis.eureka.server.serviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServiceRegisryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegisryApplication.class, args);
	}

}
