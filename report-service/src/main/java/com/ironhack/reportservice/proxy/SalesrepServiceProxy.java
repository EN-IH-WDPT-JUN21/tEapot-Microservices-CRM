package com.ironhack.reportservice.proxy;

import com.ironhack.reportservice.DTO.SalesRepDTO;
import com.ironhack.reportservice.DTO.TransactionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("salesrep-service")
public interface SalesrepServiceProxy {

    @GetMapping("/crm/salesrep/{id}")
    public SalesRepDTO getById(@PathVariable("id") Long id);

    @GetMapping("/crm/salesrep")
    List<SalesRepDTO> getAll();

    @PostMapping("/crm/salesrep")
    SalesRepDTO create(@RequestBody TransactionDTO transaction);

    @DeleteMapping("/crm/salesrep/{id}")
    void delete(@PathVariable Long id);

    @PutMapping("/crm/salesrep/{id}")
    SalesRepDTO update(@PathVariable Long id, @RequestBody TransactionDTO transaction);
}
