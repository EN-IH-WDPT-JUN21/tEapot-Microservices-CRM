package com.ironhack.reportservice.controller;

import com.ironhack.reportservice.DTO.ReportDTO;
import com.ironhack.reportservice.service.ReportService;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/crm/report")
public class ReportServiceController {

    final ReportService reportService;

    public ReportServiceController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/{command}")
    @Retry(name = "", fallbackMethod = "getReportFallback") //currently not sure how to apply to multiple services, do I need to reference a service?
    public List<ReportDTO> getReportByType(@PathVariable(name = "command") String command){
        return reportService.getReport(command);
    }

    //Fallback method //HOW DO I CALL ON MULTIPLE MICROSERVICES
    public List<ReportDTO> getReportFallback(Exception e){
        List<ReportDTO> reportOutput = new ArrayList<>();
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setLabel("The answer to everything is: ");
        reportDTO.setValue(42);
        reportOutput.add(reportDTO);
        return reportOutput;
    }
}
