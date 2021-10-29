package com.ironhack.opportunityservice.service.impl;

import com.ironhack.opportunityservice.dao.Opportunity;
import com.ironhack.opportunityservice.dto.*;
import com.ironhack.opportunityservice.enums.Product;
import com.ironhack.opportunityservice.enums.Status;
import com.ironhack.opportunityservice.proxy.AccountProxy;
import com.ironhack.opportunityservice.proxy.ContactProxy;
import com.ironhack.opportunityservice.proxy.LeadProxy;
import com.ironhack.opportunityservice.proxy.SalesRepProxy;
import com.ironhack.opportunityservice.repository.OpportunityRepository;
import com.ironhack.opportunityservice.service.interfaces.IOpportunityService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OpportunityService implements IOpportunityService {

    private final LeadProxy leadProxy;
    private final ContactProxy contactProxy;
    private final AccountProxy accountProxy;
    private final SalesRepProxy salesRepProxy;
    private final OpportunityRepository opportunityRepository;

    public OpportunityService(LeadProxy leadProxy, ContactProxy contactProxy, AccountProxy accountProxy,
                              OpportunityRepository opportunityRepository, SalesRepProxy salesRepProxy) {
        this.leadProxy = leadProxy;
        this.contactProxy = contactProxy;
        this.accountProxy = accountProxy;
        this.salesRepProxy = salesRepProxy;
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

        Opportunity opportunity = new Opportunity(convertRequest.getOpportunityDTO(), contact.getId());
        opportunity = opportunityRepository.save(opportunity);

        SalesRepDTO salesRepDTO = salesRepProxy.updateSalesRep(convertRequest.getSalesRepId(), opportunity.getId());

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
        accountProxy.deleteOpportunityFromAccounts(id);
        opportunityRepository.deleteById(id);
    }

    @Transactional
    public OpportunityDTO updateStatus(Long id, Status status) {
        Optional<Opportunity> opportunity = opportunityRepository.findById(id);
        if (opportunity.isPresent()) {
            opportunity.get().setStatus(status);
            return new OpportunityDTO(opportunity.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no Opportunity with id " + id);
        }
    }

    public List<OpportunityDTO> getByStatusAndProduct(Status status, Product product) {
        List<OpportunityDTO> opportunityDTOList = new ArrayList<>();
        if (status != null && product != null) {
            List<Opportunity> opportunityList = opportunityRepository.findByStatusAndProduct(status, product);
            for (Opportunity opportunity : opportunityList) {
                opportunityDTOList.add(new OpportunityDTO(opportunity));
            }
        } else if (status == null && product != null) {
            List<Opportunity> opportunityList = opportunityRepository.findByProduct(product);
            for (Opportunity opportunity : opportunityList) {
                opportunityDTOList.add(new OpportunityDTO(opportunity));
            }
        } else if (status != null) {
            List<Opportunity> opportunityList = opportunityRepository.findByStatus(status);
            for (Opportunity opportunity : opportunityList) {
                opportunityDTOList.add(new OpportunityDTO(opportunity));
            }
        } else {
            List<Opportunity> opportunityList = opportunityRepository.findAll();
            for (Opportunity opportunity : opportunityList) {
                opportunityDTOList.add(new OpportunityDTO(opportunity));
            }
        }
        return opportunityDTOList;
    }
}
