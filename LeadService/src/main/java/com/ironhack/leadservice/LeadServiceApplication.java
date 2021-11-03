package com.ironhack.leadservice;

import com.ironhack.leadservice.dao.Lead;
import com.ironhack.leadservice.repository.LeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableSwagger2
public class LeadServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeadServiceApplication.class, args);
	}


	//Swagger configuration, url: http://localhost:8300/swagger-ui/#/lead-controller
	@Bean
	public Docket api () {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ironhack.leadservice.controller"))
				.paths(PathSelectors.any())
				.build();
	}

//	@Component
//	public class SampleDataLoader implements CommandLineRunner {
//		@Autowired
//		LeadRepository leadRepository;
//		@Override
//		public void run(String... args) throws Exception {
//			leadRepository.saveAll(List.of(
//							new Lead("Oleg Smart", "0158158158", "Osmanrt@example.com", "Smart Solutions", 1L),
//							new Lead("Adam Biggs", "0200200200", "abiggs@example.com", "BigBiggs", 1L),
//							new Lead("Cedric Coleman", "0300300300", "ccoleman@example.com", "Cedar Coleman", 1L),
//							new Lead("Daniel Dibbs", "0458789546", "ddibbs@example.com", "Dibb IT", 2L),
//
//							new Lead("Eric Edelmann", "0532578990", "eedelmann@example.com", "ElderMan", 2L),
//							new Lead("Ferdinand Frog", "0121312122", "ffrog@example.com", "Smily Frog", 2L),
//							new Lead("Greg Goll", "0321321321", "ggoll@example.com", "Goll", 2L),
//
//							new Lead("Henry Hickman", "0432432432", "hhickmann@example.com", "Hickory", 3L),
//							new Lead("Izabella Ichtmann", "0543543543", "iichtmann@example.com", "Ichtiology Tech", 3L),
//							new Lead("Jenny Jones", "0654654654", "jjones@example.com", "Jones&Jones", 3L)
//					)
//			);
//		}
//	}
}
