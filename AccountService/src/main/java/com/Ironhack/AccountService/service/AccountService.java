package com.Ironhack.AccountService.service;

import com.Ironhack.AccountService.dao.Account;
import com.Ironhack.AccountService.dto.AccountDTO;
import com.Ironhack.AccountService.dto.TransactionDTO;
import com.Ironhack.AccountService.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public Account create(TransactionDTO transactionDTO){
        AccountDTO accountDTO= transactionDTO.getAccountDTO();
        Account account=new Account();
        account.setIndustry(accountDTO.getIndustry());
        account.setEmployeeCount(accountDTO.getEmployeeCount());
        account.setCity(accountDTO.getCity());
        account.setCountry(accountDTO.getCountry());

        if(accountDTO.getOpportunities()==null || accountDTO.getOpportunities().isEmpty()) {
            account.setOpportunities(new ArrayList<>());

        }else{
            account.setOpportunities(accountDTO.getOpportunities());
        }
        account.getOpportunities().add(transactionDTO.getOpportunityId());

        if(accountDTO.getContacts()==null || accountDTO.getContacts().isEmpty()){
            account.setContacts(new ArrayList<>());
        }else{
            account.setContacts(accountDTO.getContacts());
        }
        account.getContacts().add(transactionDTO.getContactId());

        return accountRepository.save(account);
    }



    public AccountDTO getAccountDTO(Long id){
        if(accountRepository.findById(id).isEmpty()){
            return null;
        }else {
            Account account = accountRepository.findById(id).get();
            AccountDTO accountDTO =new AccountDTO();
            accountDTO.setIndustry(account.getIndustry());
            accountDTO.setEmployeeCount(account.getEmployeeCount());
            accountDTO.setCity(account.getCity());
            accountDTO.setCountry(account.getCountry());
            if(account.getOpportunities()==null || account.getOpportunities().isEmpty()) {
                accountDTO.setOpportunities(new ArrayList<>());
            }else{
                accountDTO.setOpportunities(account.getOpportunities());
            }
            if(account.getContacts()==null || account.getContacts().isEmpty()){
                accountDTO.setContacts(new ArrayList<>());
            }else{
                accountDTO.setContacts(account.getContacts());
            }
            return accountDTO;
        }
    }


    public void deleteAccount(Long id){
        if(accountRepository.findById(id).isPresent()){
            accountRepository.delete(accountRepository.findById(id).get());
        }
    }


    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account updateAccount(Long id, TransactionDTO transaction) {
        if(accountRepository.findById(id).isEmpty()){
            return null;
        }else {
            Account account = accountRepository.findById(id).get();
            Long contact= transaction.getContactId();
            Long opportunity=transaction.getOpportunityId();
            if(!account.getContacts().contains(contact)){
                account.getContacts().add(contact);
            }
            if(!account.getOpportunities().contains(opportunity)){
                account.getOpportunities().add(opportunity);
            }
            return accountRepository.save(account);
        }

    }
}
