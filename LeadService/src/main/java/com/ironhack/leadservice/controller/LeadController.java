package com.ironhack.leadservice.controller;

import com.ironhack.leadservice.dao.Lead;
import com.ironhack.leadservice.dto.LeadDto;
import com.ironhack.leadservice.service.interfaces.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/crm/leads")
public class LeadController {

    @Autowired
    LeadService leadService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Lead findLeadById(@PathVariable(name="id") Long id) {
        return leadService.findLead(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public LeadDto createLead(@RequestBody @Valid LeadDto leadDto) {
       return leadService.createLead(leadDto);
    }

    @DeleteMapping("/{id")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteLead(@PathVariable(name="id") Long id) {
        leadService.deleteLead(id);
    }

    @GetMapping
    public List<Lead> findAllLeads() {
        return leadService.findAllLeads();
    }

}
