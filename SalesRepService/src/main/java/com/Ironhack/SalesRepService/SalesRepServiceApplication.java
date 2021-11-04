package com.Ironhack.SalesRepService;

import com.Ironhack.SalesRepService.dao.SalesRep;
import com.Ironhack.SalesRepService.repository.SalesRepRepository;
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
public class SalesRepServiceApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SalesRepServiceApplication.class, args);
	}

	@Component
	public class SampleDataLoader implements CommandLineRunner {
		@Autowired
		SalesRepRepository salesRepRepository;
		@Override
		public void run(String... args) throws Exception {
			salesRepRepository.saveAll(List.of(
							new SalesRep("Arthur Doyle", new ArrayList(List.of(1L, 2L, 3L)), new ArrayList(List.of(1L, 2L, 3L, 4L))),
							new SalesRep("Maggie Newman", new ArrayList(List.of(5L, 6L, 7L)), new ArrayList(List.of(5L, 6L, 7L))),
							new SalesRep("Oleg Brigman", new ArrayList(List.of(8L, 9L, 10L)), new ArrayList(List.of(8L, 9L, 10L))),
							new SalesRep("Suzan Smith", new ArrayList(), new ArrayList(List.of()))
					)
			);
		}
	}

}
