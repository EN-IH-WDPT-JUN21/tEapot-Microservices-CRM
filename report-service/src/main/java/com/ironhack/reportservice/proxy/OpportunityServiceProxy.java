package com.ironhack.reportservice.proxy;

import com.ironhack.reportservice.DTO.OpportunityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("opportunity-service")
public interface OpportunityServiceProxy {

    @GetMapping("/crm/opportunity")
    List<OpportunityDTO> getAllOpportunities();

    @GetMapping("/crm/opportunity/{id}")
    OpportunityDTO getOpportunityById(@PathVariable Long id);

    @GetMapping("/crm/opportunityparams")
    List<OpportunityDTO> getOpportunityBySalesRepIdForStatus(@RequestParam Long salesrepId, @RequestParam String status);

    @GetMapping("/crm/opportunityacc")
    List<OpportunityDTO> getOpportunityByAccountIdForStatus(@RequestParam Long accountId, @RequestParam String status);

}
