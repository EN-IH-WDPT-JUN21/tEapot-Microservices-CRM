package com.Ironhack.AccountService.controller;

import com.Ironhack.AccountService.dto.AccountDTO;
import com.Ironhack.AccountService.dto.TransactionDTO;
import com.Ironhack.AccountService.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/crm/account")
public class AccountController {

    @Autowired
    AccountService accountService;


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO getById(@PathVariable("id") Long id) {
        return accountService.getAccountDTO(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDTO> getAll() {
        return accountService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO create(@RequestBody TransactionDTO transaction) throws ParseException {
        return accountService.create(transaction);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDTO update(@PathVariable Long id, @RequestBody TransactionDTO transaction) {
        return accountService.updateAccount(id, transaction);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

}
