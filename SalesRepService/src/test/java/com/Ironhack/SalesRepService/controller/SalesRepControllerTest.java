package com.Ironhack.SalesRepService.controller;

import com.Ironhack.SalesRepService.dao.SalesRep;
import com.Ironhack.SalesRepService.dto.TransactionDTO;
import com.Ironhack.SalesRepService.enums.Type;
import com.Ironhack.SalesRepService.repository.SalesRepRepository;
import com.Ironhack.SalesRepService.service.SalesRepService;
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

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SalesRepControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    SalesRepService salesRepService;

    @Autowired
    SalesRepRepository salesRepRepository;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper=new ObjectMapper().findAndRegisterModules();

    private SalesRep salesRep1;
    private Long id1;
    private SalesRep salesRep2;
    private Long id2;

    @BeforeEach
    void setUp(){
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        salesRep1=new SalesRep(1000L, "Ivar the Boneless", new ArrayList<>(), new ArrayList<>());
        salesRep1=salesRepRepository.save(salesRep1);
        id1=salesRep1.getId();

        salesRep2=new SalesRep(1001L, "Bjorn Ironside", new ArrayList<>(), new ArrayList<>());
        salesRep2=salesRepRepository.save(salesRep2);
        id2=salesRep2.getId();
    }

    @AfterEach
    void teardown(){
        salesRepRepository.deleteAll();
    }

    @Test
    void create() throws Exception {
        TransactionDTO salesRep3=new TransactionDTO("Sigurd SnakeEyes", 162L, 172L);

        String body=objectMapper.writeValueAsString(salesRep3);
        MvcResult mvcResult=mockMvc.perform(post("/crm/salesrep")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Sigurd SnakeEyes"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("162"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("172"));
    }


    @Test
    void getById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/crm/salesrep/"+id1.toString()))
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Ivar the Boneless"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("Bjorn Ironside"));
    }

    @Test
    void getAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/crm/salesrep"))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("Ivar the Boneless"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Bjorn Ironside"));
    }

    @Test
    void delete() {
        assertTrue(salesRepRepository.findById(id1).isPresent());
        salesRepService.delete(id1);
        assertTrue(salesRepRepository.findById(id1).isEmpty());
    }

    @Test
    void update() throws Exception {
        TransactionDTO transaction =new TransactionDTO();
        transaction.setOpportunityId(12020L);
        transaction.setLeadId(1200L);
        transaction.setTransactionType(Type.ADD);

        String body=objectMapper.writeValueAsString(transaction);
        MvcResult mvcResult = mockMvc.perform(put("/crm/salesrep/"+id1.toString())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("12020"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1200"));

        transaction.setTransactionType(Type.REMOVE);

        body=objectMapper.writeValueAsString(transaction);
        mvcResult = mockMvc.perform(put("/crm/salesrep/"+id1.toString())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertFalse(mvcResult.getResponse().getContentAsString().contains("12020"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("1200"));


    }
}