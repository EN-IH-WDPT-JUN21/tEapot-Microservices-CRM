package com.ironhack.opportunityservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.opportunityservice.dto.AccountDTO;
import com.ironhack.opportunityservice.dto.ConvertRequest;
import com.ironhack.opportunityservice.dto.OpportunityDTO;
import com.ironhack.opportunityservice.dto.StatusDTO;
import com.ironhack.opportunityservice.enums.Industry;
import com.ironhack.opportunityservice.enums.Product;
import com.ironhack.opportunityservice.enums.Status;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OpportunityControllerTest {
    final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    OpportunityController opportunityController;
    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Order(1)
    @Test
    void getOpportunities() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/crm/opportunity")).andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("OPEN"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("CLOSED_WON"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("CLOSED_LOST"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("FLATBED"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("120"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("320"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("520"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("10"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("70"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("158"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("128"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("142"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("258"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("115"));

        mvcResult = mockMvc.perform(get("/crm/opportunity")
                .param("status", Status.OPEN.toString())
                .param("product", Product.FLATBED.toString()))
                .andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("OPEN"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("FLATBED"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("CLOSED_LOST"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("CLOSED_WON"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("HYBRID"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("BOX"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("120"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("10"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("70"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("128"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("115"));
    }

    @Order(1)
    @Test
    void getById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/crm/opportunity/1")).andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("OPEN"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("FLATBED"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("120"));
    }

    @Order(3)
    @Test
    void convertLead() throws Exception {
        String body = objectMapper.writeValueAsString(new ConvertRequest(
                new AccountDTO(),
                new OpportunityDTO(Status.OPEN, Product.BOX, 42, 1L, 1L),
                1L,
                1L
        ));

        MvcResult mvcResult = mockMvc.perform(post("/crm/opportunity/1")
                .content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("OPEN"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("BOX"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("42"));

        body = objectMapper.writeValueAsString(new ConvertRequest(
                new AccountDTO(Industry.PRODUCE, 289, "Pombal", "Portugal"),
                new OpportunityDTO(Status.OPEN, Product.HYBRID, 42, 1L, 1L),
                2L,
                1L
        ));

        mvcResult = mockMvc.perform(post("/crm/opportunity")
                .content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("OPEN"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("HYBRID"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("42"));
    }

    @Order(4)
    @Test
    void deleteOpportunity() throws Exception {
        MvcResult mvcResult = mockMvc.perform(delete("/crm/opportunity/1")).andExpect(status().isNoContent()).andReturn();

        mvcResult = mockMvc.perform(get("/crm/opportunity/1")).andExpect(status().isNotFound()).andReturn();
    }

    @Order(2)
    @Test
    void updateStatus() throws Exception {
        String body = objectMapper.writeValueAsString(new StatusDTO(Status.CLOSED_WON));

        MvcResult mvcResult = mockMvc.perform(patch("/crm/opportunity/1")
                .content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("CLOSED_WON"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("FLATBED"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("120"));
    }

    @Order(1)
    @Test
    void getByStatusAndSalesrepId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/crm/opportunity/opportunity-sales")
                .param("status", Status.OPEN.toString())
                .param("id", Integer.toString(2)))
                .andExpect(status().isOk()).andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString().contains("OPEN"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("FLATBED"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("70"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("128"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("CLOSED_LOST"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("CLOSED_WON"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("HYBRID"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("BOX"));
    }
}