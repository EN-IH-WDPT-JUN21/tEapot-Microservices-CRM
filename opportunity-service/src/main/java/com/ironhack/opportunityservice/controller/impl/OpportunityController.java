package com.ironhack.opportunityservice.controller.impl;

import com.ironhack.opportunityservice.controller.interfaces.IOpportunityController;
import com.ironhack.opportunityservice.dto.ConversionReceipt;
import com.ironhack.opportunityservice.dto.ConvertRequest;
import com.ironhack.opportunityservice.dto.OpportunityDTO;
import com.ironhack.opportunityservice.service.interfaces.IOpportunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/opportunity")
public class OpportunityController implements IOpportunityController {

    @Autowired
    IOpportunityService opportunityService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<OpportunityDTO> getOpportunities(@PathVariable("id") Long id) {
        return opportunityService.getOpportunities(id);
    }

    @PostMapping("/{account-id}")
    public ConversionReceipt convertLead(@PathVariable("account-id") Long accountId, @RequestBody ConvertRequest convertRequest) {
        return opportunityService.convertLead(accountId, convertRequest);
    }

    @Override
    public void deleteOpportunity(Long id) {
        opportunityService.deleteOpportunity(id);
    }
}
