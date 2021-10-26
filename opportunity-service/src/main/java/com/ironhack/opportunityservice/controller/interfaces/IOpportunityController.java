package com.ironhack.opportunityservice.controller.interfaces;

import com.ironhack.opportunityservice.dto.ConversionReceipt;
import com.ironhack.opportunityservice.dto.ConvertRequest;
import com.ironhack.opportunityservice.dto.OpportunityDTO;

import java.util.List;

public interface IOpportunityController {
    public List<OpportunityDTO> getOpportunities(Long id);
    public ConversionReceipt convertLead(Long id, ConvertRequest convertRequest);
    public void deleteOpportunity(Long id);
}
