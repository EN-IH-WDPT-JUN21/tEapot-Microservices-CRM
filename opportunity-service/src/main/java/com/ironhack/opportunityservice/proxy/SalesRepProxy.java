package com.ironhack.opportunityservice.proxy;

import com.ironhack.opportunityservice.dto.SalesRepDTO;
import com.ironhack.opportunityservice.dto.TransactionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("SALESREP-SERVICE")
public interface SalesRepProxy {
    @GetMapping("/crm/salesrep/{id}")
    SalesRepDTO getById(@PathVariable("id") Long id);

    @GetMapping("/crm/salesrep")
    List<SalesRepDTO> getAll();

    @PutMapping("/crm/salesrep/{id}")
    public SalesRepDTO update(@PathVariable Long id, @RequestBody TransactionDTO transaction);
}
