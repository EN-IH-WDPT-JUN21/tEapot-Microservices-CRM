package com.Ironhack.ContactService.controller;

import com.Ironhack.ContactService.dto.ContactDTO;
import com.Ironhack.ContactService.repository.ContactRepository;
import com.Ironhack.ContactService.service.ContactService;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ContactTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    ContactService contactService;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper=new ObjectMapper().findAndRegisterModules();

    private ContactDTO contact1;
    private Long id1;
    private ContactDTO contact2;
    private Long id2;

    @BeforeEach
    void setUp() throws ParseException {


        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        contact1=new ContactDTO(1L,"John Smith", "500500500", "jsmith@example.com", "Smith&Smith");
        id1=contactService.create(contact1).getId();

        contact2=new ContactDTO(2L,"Adam Jensen", "100100100", "aJensen@example.com", "Jensen&Jensen");
        id2=contactService.create(contact2).getId();
    }

    @AfterEach
    void tearDown() {
        contactRepository.deleteAll();
    }

    @Test
    void getById() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/crm/contact/"+id1.toString()))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("John Smith"));
        assertFalse(mvcResult.getResponse().getContentAsString().contains("aJensen@example.com"));

    }

    @Test
    void getAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/crm/contact"))
                .andExpect(status().isOk())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("jsmith@example.com"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Jensen&Jensen"));
    }

    @Test
    void create() throws Exception {
        ContactDTO transaction =new ContactDTO();
        transaction.setName("Ann Boleyn");
        transaction.setCompanyName("Tudor.com");
        transaction.setEmail("head@not.com");
        transaction.setId(3L);
        transaction.setPhoneNumber("1501-1905-1536");

        String body=objectMapper.writeValueAsString(transaction);
        MvcResult mvcResult=mockMvc.perform(post("/crm/contact")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1501-1905-1536"));
    }

    @Test
    void update() throws Exception {
        ContactDTO transaction =new ContactDTO();
        transaction.setName("Henry VIII Tudor");
        transaction.setCompanyName("Tudor.com");
        transaction.setEmail("man@charge.com");
        transaction.setId(3L);
        transaction.setPhoneNumber("1491-2801-1547");

        String body=objectMapper.writeValueAsString(transaction);
        MvcResult mvcResult = mockMvc.perform(put("/crm/contact/"+id1.toString())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("1491-2801-1547"));

        body=objectMapper.writeValueAsString(transaction);
        mvcResult = mockMvc.perform(put("/crm/contact/100100100")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("",mvcResult.getResponse().getContentAsString());
    }

    @Test
    void delete(){
        assertTrue(contactRepository.findById(id1).isPresent());
        contactService.delete(id1);
        assertFalse(contactRepository.findById(id1).isPresent());
    }


}