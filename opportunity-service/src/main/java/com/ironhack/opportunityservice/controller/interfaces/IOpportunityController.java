package com.ironhack.opportunityservice.controller.interfaces;

import com.ironhack.opportunityservice.dto.ConversionReceipt;
import com.ironhack.opportunityservice.dto.ConvertRequest;
import com.ironhack.opportunityservice.dto.OpportunityDTO;
import com.ironhack.opportunityservice.dto.StatusDTO;
import com.ironhack.opportunityservice.enums.Product;
import com.ironhack.opportunityservice.enums.Status;

import java.util.List;

public interface IOpportunityController {
    List<OpportunityDTO> getOpportunities(Status status, Product product);
    OpportunityDTO getById(Long id);
    ConversionReceipt convertLead(Long id, ConvertRequest convertRequest);
    ConversionReceipt convertLead(ConvertRequest convertRequest);
    void deleteOpportunity(Long id);
    OpportunityDTO updateStatus(Long id, StatusDTO status);
    List<OpportunityDTO> getByStatusAndSalesrepId(Status status, Long salesRepId);
}
