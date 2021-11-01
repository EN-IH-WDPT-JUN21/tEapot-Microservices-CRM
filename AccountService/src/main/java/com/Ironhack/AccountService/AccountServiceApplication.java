package com.Ironhack.AccountService;

import com.Ironhack.AccountService.dao.Account;
import com.Ironhack.AccountService.enums.Industry;
import com.Ironhack.AccountService.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class AccountServiceApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}


	@Component
	public class SampleDataLoader implements CommandLineRunner {
		@Autowired
		AccountRepository accountRepository;
		@Override
		public void run(String... args) throws Exception {
			accountRepository.saveAll(List.of(
					new Account("Boston", "USA", 2000, Industry.OTHER, new ArrayList(List.of(1L, 2L, 3L)), new ArrayList(List.of(1L, 2L, 3L))),
					new Account("London", "UK", 5000, Industry.MEDICAL, new ArrayList(List.of(5L, 6L, 7L)), new ArrayList(List.of(5L, 6L, 7L))),
					new Account("Madrid", "Spain", 456, Industry.PRODUCE, new ArrayList(List.of(8L, 9L, 10L)), new ArrayList(List.of(8L, 9L, 10L))),
					new Account("Berlin", "Germany", 5098, Industry.ECOMMERCE, new ArrayList(List.of(11L, 12L, 13L)), new ArrayList(List.of(11L, 12L, 13L)))

					)
			);
		}
	}
}
