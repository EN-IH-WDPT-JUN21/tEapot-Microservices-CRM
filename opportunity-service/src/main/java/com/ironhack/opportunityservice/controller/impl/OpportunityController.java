package com.ironhack.opportunityservice.controller.impl;

import com.ironhack.opportunityservice.controller.interfaces.IOpportunityController;
import com.ironhack.opportunityservice.dto.ConversionReceipt;
import com.ironhack.opportunityservice.dto.ConvertRequest;
import com.ironhack.opportunityservice.dto.OpportunityDTO;
import com.ironhack.opportunityservice.dto.StatusDTO;
import com.ironhack.opportunityservice.enums.Product;
import com.ironhack.opportunityservice.enums.Status;
import com.ironhack.opportunityservice.service.interfaces.IOpportunityService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crm/opportunity")
public class OpportunityController implements IOpportunityController {

    @Autowired
    IOpportunityService opportunityService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OpportunityDTO> getOpportunities(Status status, Product product) {
        return opportunityService.getOpportunities(status, product);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OpportunityDTO getById(@PathVariable("id") Long id) {
        return opportunityService.getById(id);
    }


    @PostMapping("/{account-id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ConversionReceipt convertLead(@PathVariable(name = "account-id") Long accountId, @RequestBody ConvertRequest convertRequest) {
        return opportunityService.convertLead(accountId, convertRequest);
    }


    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OpportunityDTO updateStatus(@PathVariable("id") Long id, @RequestBody StatusDTO status){
        return opportunityService.updateStatus(id, status);
    }

    @GetMapping("/opportunity-sales")
    @ResponseStatus(HttpStatus.OK)
    public List<OpportunityDTO> getByStatusAndSalesrepId(@RequestParam("status") Status status, @RequestParam("id") Long salesrepId){
        return opportunityService.getByStatusAndSalesrepId(status, salesrepId);
    }


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ConversionReceipt convertLead(@RequestBody ConvertRequest convertRequest) {
        return opportunityService.convertLead(null, convertRequest);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOpportunity(@PathVariable("id") Long id) {
        opportunityService.deleteOpportunity(id);
    }
}
