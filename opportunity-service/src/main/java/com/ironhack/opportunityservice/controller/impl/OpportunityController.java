package com.ironhack.opportunityservice.controller.impl;

import com.ironhack.opportunityservice.controller.interfaces.IOpportunityController;
import com.ironhack.opportunityservice.dto.ConversionReceipt;
import com.ironhack.opportunityservice.dto.ConvertRequest;
import com.ironhack.opportunityservice.dto.OpportunityDTO;
import com.ironhack.opportunityservice.enums.Product;
import com.ironhack.opportunityservice.enums.Status;
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
    public ConversionReceipt convertLead(@PathVariable("account-id") Long accountId, @RequestBody ConvertRequest convertRequest) {
        return opportunityService.convertLead(accountId, convertRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteOpportunity(@PathVariable("id") Long id) {
        opportunityService.deleteOpportunity(id);
    }

    @PatchMapping("/{id}")
    public OpportunityDTO updateStatus(@PathVariable("id") Long id, @RequestBody Status status){
        return opportunityService.updateStatus(id, status);
    }

    @GetMapping("/opportunitysales")
    public List<OpportunityDTO> getByStatusAndSalesrepId(@RequestParam("status") String status, @RequestParam("id") Long salesrepId){
        return opportunityService.getByStatusAndSalesrepId(status, salesrepId);
    }
}
