package com.ironhack.opportunityservice.proxy;

import com.ironhack.opportunityservice.dto.ContactDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("contact-service")
public interface ContactProxy {

    @PostMapping("/crm/contact")
    ContactDTO createContact(@RequestBody ContactDTO contactDTO);
}
