package com.ironhack.reportservice.proxy;

import com.ironhack.reportservice.DTO.OpportunityDTO;
import com.ironhack.reportservice.enums.Status;
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
    OpportunityDTO getById(@PathVariable Long id);

    @GetMapping("/crm/opportunity/opportunitysales")
    List<OpportunityDTO> getByStatusAndSalesrepId(@RequestParam("status") Status status, @RequestParam("id")Long salesrepId);


}
