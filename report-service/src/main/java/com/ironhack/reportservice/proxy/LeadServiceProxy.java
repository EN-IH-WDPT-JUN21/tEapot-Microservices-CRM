package com.ironhack.reportservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("lead-service")
public interface LeadServiceProxy {
}
