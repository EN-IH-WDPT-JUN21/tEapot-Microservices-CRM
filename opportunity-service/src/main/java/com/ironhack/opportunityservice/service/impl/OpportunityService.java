package com.ironhack.opportunityservice.service.impl;

import com.ironhack.opportunityservice.dao.Opportunity;
import com.ironhack.opportunityservice.dto.*;
import com.ironhack.opportunityservice.proxy.AccountProxy;
import com.ironhack.opportunityservice.proxy.ContactProxy;
import com.ironhack.opportunityservice.proxy.LeadProxy;
import com.ironhack.opportunityservice.repository.OpportunityRepository;
import com.ironhack.opportunityservice.service.interfaces.IOpportunityService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OpportunityService implements IOpportunityService {

    private final LeadProxy leadProxy;
    private final ContactProxy contactProxy;
    private final AccountProxy accountProxy;
    private final OpportunityRepository opportunityRepository;

    public OpportunityService(LeadProxy leadProxy, ContactProxy contactProxy, AccountProxy accountProxy, OpportunityRepository opportunityRepository) {
        this.leadProxy = leadProxy;
        this.contactProxy = contactProxy;
        this.accountProxy = accountProxy;
        this.opportunityRepository = opportunityRepository;
    }

    public List<OpportunityDTO> getOpportunities(Long id) {
        if (id != null) {
            Optional<Opportunity>  opportunity = opportunityRepository.findById(id);
            if (opportunity.isPresent()) {
                return List.of(new OpportunityDTO(opportunity.get()));
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no Opportunity with id " + id);
            }
        } else {
            List<OpportunityDTO> opportunityDTOList = new ArrayList<>();
            for (Opportunity opportunity : opportunityRepository.findAll()) {
                opportunityDTOList.add(new OpportunityDTO(opportunity));
            }
            return opportunityDTOList;
        }
    }


    public ConversionReceipt convertLead(Long accountId, ConvertRequest convertRequest) {
        LeadDTO lead = leadProxy.deleteLead(convertRequest.getLeadId());
        if (lead == null) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Lead was not deleted! Aborting request...");
        }
        ContactDTO contact = new ContactDTO(lead);
        contact = contactProxy.createContact(contact);
        if (contact == null) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Contact was not created! Aborting request...");
        }

        Opportunity opportunity = new Opportunity(convertRequest.getOpportunityDTO(), contact.getId(), convertRequest.getSalesRepId());
        opportunity = opportunityRepository.save(opportunity);

        AccountDTO account;
        if (accountId == null) {
            AccountCreationDTO accountRequest = new AccountCreationDTO(convertRequest.getAccountDTO(), opportunity.getId(), contact.getId());
            account = accountProxy.createAccount(accountRequest);
        } else {
            AccountUpdateDTO accountRequest = new AccountUpdateDTO(opportunity.getId(), contact.getId());
            account = accountProxy.updateAccount(accountId, accountRequest);
        }

        ConversionReceipt conversionReceipt = new ConversionReceipt(convertRequest.getLeadId(), contact, new OpportunityDTO(opportunity), account);
        return conversionReceipt;
    }


    public void deleteOpportunity(Long id) {

    }
}
