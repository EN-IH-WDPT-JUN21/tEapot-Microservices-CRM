package com.ironhack.leadservice.proxy;

import com.ironhack.leadservice.dto.SalesRepDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient("SALESREP-SERVICE")
public interface SalesRepProxy {

    @GetMapping("/crm/salesrep/{id}")
    SalesRepDto getById(@PathVariable(name="id") Long id);

    @PostMapping("/crm/salesrep")
    SalesRepDto createSalesRep(@RequestBody SalesRepDto salesRepDto);

    @PutMapping("/crm/salesrep/{id}")
    @ResponseStatus(HttpStatus.OK)
    SalesRepDto update(@PathVariable Long id, @RequestBody SalesRepDto salesRepDto);
}
