package com.ironhack.opportunityservice.proxy;

import com.ironhack.opportunityservice.dto.SalesRepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient("SALESREP-SERVICE")
public interface SalesRepProxy {
    @GetMapping("/crm/salesrep/{id}")
    SalesRepDTO getById(@PathVariable("id") Long id);

    @GetMapping("/crm/salesrep")
    List<SalesRepDTO> getAll();
}
