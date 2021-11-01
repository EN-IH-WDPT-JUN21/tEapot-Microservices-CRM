package com.ironhack.opportunityservice;

import com.ironhack.opportunityservice.dao.Opportunity;
import com.ironhack.opportunityservice.enums.Product;
import com.ironhack.opportunityservice.enums.Status;
import com.ironhack.opportunityservice.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class OpportunityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpportunityServiceApplication.class, args);
	}

	@Component
	public class SampleDataLoader implements CommandLineRunner {
		@Autowired
		OpportunityRepository opportunityRepository;
		@Override
		public void run(String... args) throws Exception {
			opportunityRepository.saveAll(List.of(
							new Opportunity(Status.OPEN, Product.FLATBED, 120, 1L, 1L),
							new Opportunity(Status.CLOSED_LOST, Product.FLATBED, 320, 1L, 1L),
							new Opportunity(Status.CLOSED_WON, Product.FLATBED, 520, 2L, 1L),
							new Opportunity(Status.OPEN, Product.FLATBED, 10, 2L, 1L),
							new Opportunity(Status.OPEN, Product.FLATBED, 70, 3L, 2L),
							new Opportunity(Status.CLOSED_LOST, Product.FLATBED, 158, 3L, 2L),
							new Opportunity(Status.OPEN, Product.FLATBED, 128, 4L, 2L),
							new Opportunity(Status.CLOSED_WON, Product.FLATBED, 142, 4L, 3L),
							new Opportunity(Status.CLOSED_WON, Product.FLATBED, 258, 5L, 3L),
							new Opportunity(Status.OPEN, Product.FLATBED, 115, 1L, 3L)
					)
			);
		}
	}

}
