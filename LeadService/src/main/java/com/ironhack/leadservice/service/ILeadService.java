package com.ironhack.leadservice.service;

import com.ironhack.leadservice.dao.Lead;
import com.ironhack.leadservice.dto.LeadDto;
import com.ironhack.leadservice.dto.SalesRepDto;
import com.ironhack.leadservice.proxy.SalesRepProxy;
import com.ironhack.leadservice.repository.LeadRepository;
import com.ironhack.leadservice.service.interfaces.LeadService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ILeadService implements LeadService {

    @Autowired
    LeadRepository leadRepository;

    @Autowired
    SalesRepProxy salesRepProxy;

    @Override
    public void deleteLead(Long id) {
        Optional<Lead> existingLead = leadRepository.findById(id);

        if(existingLead.isPresent())
            leadRepository.deleteById(id);
    }

    @Override
    public Lead findLead(Long id) {
        Optional<Lead> existingLead = leadRepository.findById(id);

        if(existingLead.isEmpty())
            throw new EntityNotFoundException("There is no lead with id "+id);

        return existingLead.get();
    }

    @Override
    public List<Lead> findAllLeads() {
        return leadRepository.findAll();
    }

    @Override
    @Transactional
    public LeadDto createLead(LeadDto leadDto) {
        SalesRepDto salesRepDto;

        if(leadDto.getSalesRep().getId() == null) {
            salesRepDto = salesRepProxy.createSalesRep(new SalesRepDto(leadDto.getSalesRep().getName()));
        } else {
            salesRepDto = salesRepProxy.findSalesRep(leadDto.getSalesRep().getId());
        }

        if(salesRepDto==null)
            throw new EntityNotFoundException("Cannot create or find salesRep with provided parameters");

        Lead newLead = leadRepository.save(
                new Lead(
                        leadDto.getName(),
                        leadDto.getPhoneNumber(),
                        leadDto.getCompanyName(),
                        leadDto.getEmail(),
                        salesRepDto.getId()));

        return new LeadDto(newLead.getId(),
                newLead.getName(),
                newLead.getPhoneNumber(),
                newLead.getEmail(),
                newLead.getCompanyName(),
                salesRepDto);
    }
}