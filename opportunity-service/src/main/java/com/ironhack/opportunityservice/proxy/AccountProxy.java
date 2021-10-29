package com.ironhack.opportunityservice.proxy;

import com.ironhack.opportunityservice.dto.AccountCreationDTO;
import com.ironhack.opportunityservice.dto.AccountDTO;
import com.ironhack.opportunityservice.dto.AccountUpdateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("account-proxy")
public interface AccountProxy {

    @PostMapping("/crm/account")
    AccountDTO createAccount(AccountCreationDTO accountDTO);

    @PutMapping("/crm/account/{id}")
    AccountDTO updateAccount(@PathVariable("id") Long id, AccountUpdateDTO accountDTO);

    @PatchMapping(path = "/crm/account{id}")
    AccountDTO deleteOpportunityFromAccounts(@PathVariable("opportunity_id") Long id);
}
