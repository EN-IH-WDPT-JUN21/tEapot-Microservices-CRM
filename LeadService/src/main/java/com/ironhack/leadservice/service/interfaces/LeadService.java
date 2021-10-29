package com.ironhack.leadservice.service.interfaces;

import com.ironhack.leadservice.dao.Lead;
import com.ironhack.leadservice.dto.LeadDto;

import java.util.List;

public interface LeadService {

    void deleteLead(Long id);
    Lead findLead(Long id);
    List<Lead> findAllLeads();
    LeadDto createLead(LeadDto leadDto);

}
