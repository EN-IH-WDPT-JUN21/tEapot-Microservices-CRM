package com.ironhack.opportunityservice.service.interfaces;

import com.ironhack.opportunityservice.dto.ConversionReceipt;
import com.ironhack.opportunityservice.dto.ConvertRequest;
import com.ironhack.opportunityservice.dto.OpportunityDTO;

import java.util.List;

public interface IOpportunityService {
    public List<OpportunityDTO> getOpportunities(Long id);

    public ConversionReceipt convertLead(Long id, ConvertRequest convertRequest);

    public void deleteOpportunity(Long id);
}
