package com.ironhack.opportunityservice.controller.interfaces;

import com.ironhack.opportunityservice.dto.ConversionReceipt;
import com.ironhack.opportunityservice.dto.ConvertRequest;
import com.ironhack.opportunityservice.dto.OpportunityDTO;
import com.ironhack.opportunityservice.enums.Product;
import com.ironhack.opportunityservice.enums.Status;

import java.util.List;

public interface IOpportunityController {
    List<OpportunityDTO> getOpportunities();
    OpportunityDTO getById(Long id);
    ConversionReceipt convertLead(Long id, ConvertRequest convertRequest);
    void deleteOpportunity(Long id);
    OpportunityDTO updateStatus(Long id, Status status);
    List<OpportunityDTO> getByStatusAndProduct(Status status, Product product);
}
