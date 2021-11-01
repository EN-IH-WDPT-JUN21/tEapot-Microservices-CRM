package com.ironhack.reportservice.proxy;

import com.ironhack.reportservice.DTO.AccountDTO;
import com.ironhack.reportservice.DTO.TransactionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("ACCOUNT-SERVICE")
public interface AccountServiceProxy {

    @GetMapping("/crm/account/{id}")
    public AccountDTO getById(@PathVariable("id") Long id);

    @GetMapping("/crm/account")
    public List<AccountDTO> getAll();

    @PostMapping("/crm/account")
    public AccountDTO create(@RequestBody TransactionDTO transaction);

    @PutMapping("/crm/account/{id}")
    public AccountDTO update(@PathVariable Long id, @RequestBody TransactionDTO transaction);

    @DeleteMapping("/crm/account/{id}")
    public void delete(@PathVariable Long id);
}
