package com.Ironhack.ContactService;

import com.Ironhack.ContactService.dao.Contact;
import com.Ironhack.ContactService.repository.ContactRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class ContactServiceApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(ContactServiceApplication.class, args);
	}

	@Component
	public class SampleDataLoader implements CommandLineRunner {
		@Autowired
		ContactRepository contactRepository;
		@Override
		public void run(String... args) throws Exception {
			contactRepository.saveAll(List.of(
					new Contact("Adam Jensen", "568-456-863", "ajensen@example.com", "SampleCompany"),
					new Contact("Nicki Hall", "100-589-756", "nhall@example.com", "nicky&nicky Company"),
					new Contact("Eric Portman", "563-786-357", "eric.portman@example.com", "Portman Industries"),
					new Contact("Sofia Ackels", "458-789-546", "sofiasofiaa@example.com", "Ackels.co"),
					new Contact("Henry Schmitt", "532-578-957", "henry.schmitt@example.com", "Bright Bulbs")
					)
			);
		}
	}
}
