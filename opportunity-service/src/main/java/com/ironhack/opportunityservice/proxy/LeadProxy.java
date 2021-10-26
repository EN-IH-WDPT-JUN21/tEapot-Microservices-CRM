package com.ironhack.opportunityservice.proxy;

import com.ironhack.opportunityservice.dto.LeadDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("lead-service")
public interface LeadProxy {

    @DeleteMapping("/crm/lead/{id}")
    LeadDTO deleteLead(@PathVariable("id") Long id);
}
