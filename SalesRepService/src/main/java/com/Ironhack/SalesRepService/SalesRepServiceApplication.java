package com.Ironhack.SalesRepService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SalesRepServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesRepServiceApplication.class, args);
	}

}
