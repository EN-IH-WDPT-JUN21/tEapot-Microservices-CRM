package com.ironhack.leadservice.proxy;

import com.ironhack.leadservice.dto.SalesRepDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("SALESREP-SERVICE")
public interface SalesRepProxy {

    @GetMapping("/crm/salesrep/{id}")
    SalesRepDto findSalesRep(@PathVariable(name="id") Long id);

    @PostMapping("/crm/salesrep")
    SalesRepDto createSalesRep(@RequestBody SalesRepDto salesRepDto);
}
