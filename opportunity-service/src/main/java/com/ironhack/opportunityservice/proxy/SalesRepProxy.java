package com.ironhack.opportunityservice.proxy;

import com.ironhack.opportunityservice.dto.SalesRepDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("salesrep-service")
public interface SalesRepProxy {
    @PutMapping("/crm/salesrep{id}")
    SalesRepDTO updateSalesRep(@PathVariable("id") Long id, Long opportunityId);
}
