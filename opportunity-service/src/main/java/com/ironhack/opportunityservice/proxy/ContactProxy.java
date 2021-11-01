package com.ironhack.opportunityservice.proxy;

import com.ironhack.opportunityservice.dto.ContactDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("contact-service")
public interface ContactProxy {

    @PostMapping("/crm/contact")
    ContactDTO createContact(ContactDTO contactDTO);
}
