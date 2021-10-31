package com.Ironhack.AccountService.controller;

import com.Ironhack.AccountService.dto.TransactionDTO;
import com.Ironhack.AccountService.enums.Industry;
import com.Ironhack.AccountService.repository.AccountRepository;
import com.Ironhack.AccountService.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AccountControllerTest {
    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper=new ObjectMapper().findAndRegisterModules();

    private TransactionDTO account1;
    private Long id1;
    private TransactionDTO account2;
    private Long id2;

    @BeforeEach
    void setUp() throws ParseException {
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        account1=new TransactionDTO(1000L, Industry.OTHER,100,"London", "UK", 123L, 128L);
        id1=accountService.create(account1).getId();

        account2=new TransactionDTO(1002L, Industry.OTHER,500,"Washington", "USA", 1L, 2L);
        id2=accountService.create(account2).getId();
    }

    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }

    @Test
    void getById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/crm/account/"+id1.toString()))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("London"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Washington"));
    }

    @Test
    void create() throws Exception{
        TransactionDTO transaction =new TransactionDTO();
        transaction.setCity("London");
        transaction.setCountry("UK");
        transaction.setContactId(256L);
        transaction.setEmployeeCount(589);
        transaction.setIndustry(Industry.OTHER);
        transaction.setContactId(12L);
        transaction.setContactId(21L);

        String body=objectMapper.writeValueAsString(transaction);
        MvcResult mvcResult=mockMvc.perform(post("/crm/account")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("589"));
    }

    @Test
    void getAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/crm/account"))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("London"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Washington"));
    }

    @Test
    void update() throws Exception {
        TransactionDTO transaction =new TransactionDTO();
        transaction.setContactId(12020L);
        transaction.setOpportunityId(1200L);

        String body=objectMapper.writeValueAsString(transaction);
        MvcResult mvcResult = mockMvc.perform(put("/crm/account/"+id1.toString())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("12020"));
    }

}