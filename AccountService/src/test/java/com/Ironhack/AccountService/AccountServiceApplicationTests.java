package com.Ironhack.AccountService;

import com.Ironhack.AccountService.dao.Account;
import com.Ironhack.AccountService.dto.AccountDTO;
import com.Ironhack.AccountService.dto.TransactionDTO;
import com.Ironhack.AccountService.enums.Industry;
import com.Ironhack.AccountService.repository.AccountRepository;
import com.Ironhack.AccountService.service.AccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AccountServiceApplicationTests {

	@Autowired
	AccountService accountService;

	@Autowired
	AccountRepository accountRepository;

	private Account account1;
	private Long id1;
	private Account account2;
	private Long id2;

	@BeforeEach
	void setUp(){
		account1=new Account(1000L, Industry.OTHER,100,"London", "UK", new ArrayList<>(), new ArrayList<>());
		account1=accountRepository.save(account1);
		id1=account1.getId();

		account2=new Account(1000L, Industry.OTHER,530,"Washington", "USA", new ArrayList<>(), new ArrayList<>());
		account2=accountRepository.save(account2);
		id2=account2.getId();
	}

	@AfterEach
	void teardown(){
		accountRepository.deleteAll();
	}

	@Test
	void create() throws ParseException {
		TransactionDTO account3=new TransactionDTO(1000L, Industry.OTHER,1100,"Warsaw", "Poland", 152L, 132L);
		AccountDTO account3DTO=accountService.create(account3);
		Long id3=account3DTO.getId();
		System.out.println(accountRepository.findById(id3).get().getEmployeeCount());
		assertEquals(1100, accountRepository.findById(id3).get().getEmployeeCount());
		assertEquals(Industry.OTHER, accountRepository.findById(id3).get().getIndustry());
		assertEquals("Warsaw", accountRepository.findById(id3).get().getCity());
		assertEquals("Poland", accountRepository.findById(id3).get().getCountry());
		assertTrue(accountRepository.findById(id3).get().getContacts().contains(132L));
		assertTrue(accountRepository.findById(id3).get().getOpportunities().contains(152L));

		assertEquals(1100, account3DTO.getEmployeeCount());
		assertEquals(Industry.OTHER, account3DTO.getIndustry());
		assertEquals("Warsaw", account3DTO.getCity());
		assertEquals("Poland", account3DTO.getCountry());
		assertTrue(account3DTO.getContacts().contains(132L));
		assertTrue(account3DTO.getOpportunities().contains(152L));
	}

	@Test
	void update(){
		TransactionDTO transaction=new TransactionDTO();
		transaction.setContactId(100200L);
		transaction.setOpportunityId(100300L);
		AccountDTO acc=accountService.updateAccount(id2,transaction);
		assertTrue(accountRepository.findById(id2).get().getContacts().contains(100200L));
		assertTrue(accountRepository.findById(id2).get().getOpportunities().contains(100300L));
		assertTrue(acc.getContacts().contains(100200L));
		assertTrue(acc.getOpportunities().contains(100300L));
	}

	@Test
	void convertToDTO(){
		account1=new Account(1000L, Industry.OTHER,100,"London", "UK", new ArrayList<>(), new ArrayList<>());
		AccountDTO account1DTO=accountService.convertToDto(account1);
		assertEquals(1000L, account1DTO.getId());
		assertEquals("London", account1DTO.getCity());
	}

	@Test
	void getAccountDTO(){
		AccountDTO aDTO=accountService.getAccountDTO(id1);
		assertEquals(aDTO.getCity(), account1.getCity());
	}

	@Test
	void delete(){
		accountService.deleteAccount(id1);
		assertTrue(accountRepository.findById(id1).isEmpty());
	}

}
