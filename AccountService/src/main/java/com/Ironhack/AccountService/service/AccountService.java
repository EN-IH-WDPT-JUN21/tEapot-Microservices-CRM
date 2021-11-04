package com.Ironhack.AccountService.service;

import com.Ironhack.AccountService.dao.Account;
import com.Ironhack.AccountService.dto.AccountDTO;
import com.Ironhack.AccountService.dto.TransactionDTO;
import com.Ironhack.AccountService.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;


        public AccountDTO create(TransactionDTO transactionDTO) throws ParseException {
        Account account=convertToEntity(transactionDTO);

        //add opportunity if provided in transaction and absent in accountDTO
        if(account.getOpportunities()==null) {
            account.setOpportunities(new ArrayList<>());
        }
        if(transactionDTO.getOpportunityId()!=null) {
            if(!account.getOpportunities().contains(transactionDTO.getOpportunityId())) {
                account.getOpportunities().add(transactionDTO.getOpportunityId());
            }
        }
        //add contact if provided in transaction and absent in accountDTO
        if(account.getContacts()==null){
            account.setContacts(new ArrayList<>());
        }
        if(transactionDTO.getContactId()!=null) {
            if (!account.getContacts().contains(transactionDTO.getContactId())) {
                account.getContacts().add(transactionDTO.getContactId());
            }
        }
        account = accountRepository.save(account);
        return convertToDto(account);
    }



    public List<AccountDTO> getAll() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public AccountDTO getAccountDTO(Long id){
        if(accountRepository.findById(id).isEmpty()){
            return null;
        }else {
            Account account = accountRepository.findById(id).get();
            return convertToDto(account);
        }
    }

    public void deleteAccount(Long id){
        if(accountRepository.findById(id).isPresent()){
            accountRepository.delete(accountRepository.findById(id).get());
        }
    }

    public AccountDTO updateAccount(Long id, TransactionDTO transaction) {
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
            account=accountRepository.save(account);
            return modelMapper.map(account, AccountDTO.class);

        }
    }

    public AccountDTO convertToDto(Account account) {
        AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);
        return accountDTO;
    }

    public Account convertToEntity(TransactionDTO postDto) throws ParseException {
        Account account = modelMapper.map(postDto, Account.class);
        return account;
    }
}
