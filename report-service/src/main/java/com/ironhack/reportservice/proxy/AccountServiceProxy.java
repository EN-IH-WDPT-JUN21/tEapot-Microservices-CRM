package com.ironhack.reportservice.proxy;

import com.ironhack.reportservice.DTO.AccountDTO;
import com.ironhack.reportservice.DTO.TransactionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("ACCOUNT-SERVICE")
public interface AccountServiceProxy {

    @GetMapping("/crm/account/{id}")
    AccountDTO getById(@PathVariable("id") Long id);

    @GetMapping("/crm/account")
    List<AccountDTO> getAll();

}
