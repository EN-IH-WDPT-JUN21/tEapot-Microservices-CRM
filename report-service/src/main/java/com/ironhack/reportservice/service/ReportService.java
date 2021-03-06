package com.ironhack.reportservice.service;

import com.ironhack.reportservice.DTO.AccountDTO;
import com.ironhack.reportservice.DTO.OpportunityDTO;
import com.ironhack.reportservice.DTO.ReportDTO;
import com.ironhack.reportservice.DTO.SalesRepDTO;
import com.ironhack.reportservice.enums.ReportCommands;
import com.ironhack.reportservice.enums.Status;
import com.ironhack.reportservice.proxy.AccountServiceProxy;
import com.ironhack.reportservice.proxy.OpportunityServiceProxy;
import com.ironhack.reportservice.proxy.SalesrepServiceProxy;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    SalesrepServiceProxy salesrepServiceProxy;

    @Autowired
    OpportunityServiceProxy opportunityServiceProxy;

    @Autowired
    AccountServiceProxy accountServiceProxy;

    //**** REPORTS BY REPORT TYPE ****

    //this will take command string as input and return a report DTO which is a combo of label and value
    public List<ReportDTO> getReport(String reportCommand) {
        List<ReportDTO> reportOutput = new ArrayList<>();
        var salesreps = salesrepServiceProxy.getAll();
        var accounts = accountServiceProxy.getAll();
        var opportunities = opportunityServiceProxy.getAllOpportunities();

        switch (ReportCommands.getCommandType(reportCommand)) {

            //Get number of leads by sales representative
            case REPORT_LEAD_BY_SALESREP:
                for (SalesRepDTO salesRepDTO : salesreps){
                    ReportDTO reportDTO = new ReportDTO();
                    reportDTO.setLabel(salesRepDTO.getName());
                    reportDTO.setValue(salesRepDTO.getLeads().size());
                    reportOutput.add(reportDTO);
                }
                break;

            //Get number of opportunities by sales representative
            case REPORT_OPP_BY_SALESREP:
                for (SalesRepDTO salesRepDTO : salesreps){
                    ReportDTO reportDTO = new ReportDTO();
                    reportDTO.setLabel(salesRepDTO.getName());
                    reportDTO.setValue(salesRepDTO.getOpportunities().size());
                    reportOutput.add(reportDTO);
                }
                break;

            //Get number of opportunities with CLOSE-WON status by sales representative
            case REPORT_CLOSE_W_BY_SALESREP:
                for (SalesRepDTO salesRepDTO : salesreps){
                    ReportDTO reportDTO = new ReportDTO();
                    reportDTO.setLabel(salesRepDTO.getName());
                    reportDTO.setValue(opportunityServiceProxy.getByStatusAndSalesrepId(Status.CLOSED_WON, salesRepDTO.getId()).size());
                    reportOutput.add(reportDTO);
                }
                break;

            //Get number of opportunities with CLOSE-LOST status by sales representative
            case REPORT_CLOSE_L_BY_SALESREP:
                for (SalesRepDTO salesRepDTO : salesreps){
                    ReportDTO reportDTO = new ReportDTO();
                    reportDTO.setLabel(salesRepDTO.getName());
                    reportDTO.setValue(opportunityServiceProxy.getByStatusAndSalesrepId(Status.CLOSED_LOST, salesRepDTO.getId()).size());
                    reportOutput.add(reportDTO);
                }
                break;

            //Get number of opportunities with OPEN status by sales representative
            case REPORT_OPEN_BY_SALESREP:
                for (SalesRepDTO salesRepDTO : salesreps){
                    ReportDTO reportDTO = new ReportDTO();
                    reportDTO.setLabel(salesRepDTO.getName());
                    reportDTO.setValue(opportunityServiceProxy.getByStatusAndSalesrepId(Status.OPEN, salesRepDTO.getId()).size());
                    reportOutput.add(reportDTO);
                }
                break;

            //Get number of opportunities by product type
            case REPORT_OPP_BY_PRODUCT:
                Map<String, List<OpportunityDTO>> oppsByProduct =
                        opportunities.stream().collect(Collectors.groupingBy(w -> w.getProduct().toString()));
                for (String key : oppsByProduct.keySet()) {
                    List<OpportunityDTO> value = oppsByProduct.get(key);
                    ReportDTO reportDTO = new ReportDTO();
                    reportDTO.setLabel(key);
                    reportDTO.setValue(value.size());
                    reportOutput.add(reportDTO);
                }
                break;

            //Get number of opportunities with CLOSE-WON status by product
            case REPORT_CLOSE_W_BY_PRODUCT:
                Map<String, List<OpportunityDTO>> oppsWByProduct =
                        opportunities.stream().filter(w -> w.getStatus().equals(Status.CLOSED_WON))
                                .collect(Collectors.groupingBy(w -> w.getProduct().toString()));
                for (String key : oppsWByProduct.keySet()) {
                    List<OpportunityDTO> value = oppsWByProduct.get(key);
                    ReportDTO reportDTO = new ReportDTO();
                    reportDTO.setLabel(key);
                    reportDTO.setValue(value.size());
                    reportOutput.add(reportDTO);
                }
                break;

            //Get number of opportunities with CLOSE-LOST status by product
            case REPORT_CLOSE_L_BY_PRODUCT:
                Map<String, List<OpportunityDTO>> oppsLByProduct =
                        opportunities.stream().filter(w -> w.getStatus().equals(Status.CLOSED_LOST))
                                .collect(Collectors.groupingBy(w -> w.getProduct().toString()));
                for (String key : oppsLByProduct.keySet()) {
                    List<OpportunityDTO> value = oppsLByProduct.get(key);
                    ReportDTO reportDTO = new ReportDTO();
                    reportDTO.setLabel(key);
                    reportDTO.setValue(value.size());
                    reportOutput.add(reportDTO);
                }
                break;

            //Get number of opportunities with OPEN status by product
            case REPORT_OPEN_BY_PRODUCT:
                Map<String, List<OpportunityDTO>> oppsOByProduct =
                        opportunities.stream().filter(w -> w.getStatus().equals(Status.OPEN))
                                .collect(Collectors.groupingBy(w -> w.getProduct().toString()));
                for (String key : oppsOByProduct.keySet()) {
                    List<OpportunityDTO> value = oppsOByProduct.get(key);
                    ReportDTO reportDTO = new ReportDTO();
                    reportDTO.setLabel(key);
                    reportDTO.setValue(value.size());
                    reportOutput.add(reportDTO);
                }
                break;

            //Get number of opportunities by country
            case REPORT_OPP_BY_COUNTRY:
                Map<String, List<AccountDTO>> oppsByCountry =
                        accounts.stream().collect(Collectors.groupingBy(w -> w.getCountry()));
                for (String key : oppsByCountry.keySet()) {
                    List<AccountDTO> value = oppsByCountry.get(key);
                    ReportDTO reportDTO = new ReportDTO();
                    reportDTO.setLabel(key);
                    int oppNumber = 0;
                    for (AccountDTO accountDTO : value) {
                        oppNumber += accountDTO.getOpportunities().size();
                    }
                    reportDTO.setValue(oppNumber);
                    reportOutput.add(reportDTO);
                }
                break;

            //Get number of opportunities with CLOSE-WON status by country
            case REPORT_CLOSE_W_BY_COUNTRY:
                Map<String, List<AccountDTO>> oppsWByCountry =
                        accounts.stream().collect(Collectors.groupingBy(w -> w.getCountry()));
                for (String key : oppsWByCountry.keySet()) {
                    List<AccountDTO> value = oppsWByCountry.get(key);
                    ReportDTO reportDTO = new ReportDTO();
                    reportDTO.setLabel(key);
                    int oppNumber = 0;
                    for (AccountDTO accountDTO : value) {
                        List<Long> oppsByAcc = accountDTO.getOpportunities();
                        for(Long oppId: oppsByAcc){
                            if(opportunityServiceProxy.getById(oppId).getStatus().equals(Status.CLOSED_WON)){
                                oppNumber += 1;
                            }
                        }
                    }
                    reportDTO.setValue(oppNumber);
                    reportOutput.add(reportDTO);
                }
                break;

            //Get number of opportunities with CLOSE-LOST status by country
            case REPORT_CLOSE_L_BY_COUNTRY:
                Map<String, List<AccountDTO>> oppsLByCountry =
                        accounts.stream().collect(Collectors.groupingBy(w -> w.getCountry()));
                for (String key : oppsLByCountry.keySet()) {
                    List<AccountDTO> value = oppsLByCountry.get(key);
                    ReportDTO reportDTO = new ReportDTO();
                    reportDTO.setLabel(key);
                    int oppNumber = 0;
                    for (AccountDTO accountDTO : value) {
                        List<Long> oppsByAcc = accountDTO.getOpportunities();
                        for(Long oppId: oppsByAcc){
                            if(opportunityServiceProxy.getById(oppId).getStatus().equals(Status.CLOSED_LOST)){
                                oppNumber += 1;
                            }
                        }
                    }
                    reportDTO.setValue(oppNumber);
                    reportOutput.add(reportDTO);
                }
                break;

            //Get number of opportunities with OPEN status by country
            case REPORT_OPEN_BY_COUNTRY:
                Map<String, List<AccountDTO>> oppsOByCountry =
                        accounts.stream().collect(Collectors.groupingBy(w -> w.getCountry()));
                for (String key : oppsOByCountry.keySet()) {
                    List<AccountDTO> value = oppsOByCountry.get(key);
                    ReportDTO reportDTO = new ReportDTO();
                    reportDTO.setLabel(key);
                    int oppNumber = 0;
                    for (AccountDTO accountDTO : value) {
                        List<Long> oppsByAcc = accountDTO.getOpportunities();
                        for(Long oppId: oppsByAcc){
                            if(opportunityServiceProxy.getById(oppId).getStatus().equals(Status.OPEN)){
                                oppNumber += 1;
                            }
                        }
                    }
                    reportDTO.setValue(oppNumber);
                    reportOutput.add(reportDTO);
                }
                break;

            //Get number of opportunities with by city
            case REPORT_OPP_BY_CITY:
                Map<String, List<AccountDTO>> oppsByCity =
                        accounts.stream().collect(Collectors.groupingBy(w -> w.getCity()));
                for (String key : oppsByCity.keySet()) {
                    List<AccountDTO> value = oppsByCity.get(key);
                    ReportDTO reportDTO = new ReportDTO();
                    reportDTO.setLabel(key);
                    int oppNumber = 0;
                    for (AccountDTO accountDTO : value) {
                        oppNumber += accountDTO.getOpportunities().size();
                    }
                    reportDTO.setValue(oppNumber);
                    reportOutput.add(reportDTO);
                }
                break;

            //Get number of opportunities with CLOSE-WON status by city
            case REPORT_CLOSE_W_BY_CITY:
                Map<String, List<AccountDTO>> oppsWByCity =
                        accounts.stream().collect(Collectors.groupingBy(w -> w.getCity()));
                for (String key : oppsWByCity.keySet()) {
                    List<AccountDTO> value = oppsWByCity.get(key);
                    ReportDTO reportDTO = new ReportDTO();
                    reportDTO.setLabel(key);
                    int oppNumber = 0;
                    for (AccountDTO accountDTO : value) {
                        List<Long> oppsByAcc = accountDTO.getOpportunities();
                        for(Long oppId: oppsByAcc){
                            if(opportunityServiceProxy.getById(oppId).getStatus().equals(Status.CLOSED_WON)){
                                oppNumber += 1;
                            }
                        }
                    }
                    reportDTO.setValue(oppNumber);
                    reportOutput.add(reportDTO);
                }
                break;

            //Get number of opportunities with CLOSE-LOST status by city
            case REPORT_CLOSE_L_BY_CITY:
                Map<String, List<AccountDTO>> oppsLByCity =
                        accounts.stream().collect(Collectors.groupingBy(w -> w.getCity()));
                for (String key : oppsLByCity.keySet()) {
                    List<AccountDTO> value = oppsLByCity.get(key);
                    ReportDTO reportDTO = new ReportDTO();
                    reportDTO.setLabel(key);
                    int oppNumber = 0;
                    for (AccountDTO accountDTO : value) {
                        List<Long> oppsByAcc = accountDTO.getOpportunities();
                        for(Long oppId: oppsByAcc){
                            if(opportunityServiceProxy.getById(oppId).getStatus().equals(Status.CLOSED_LOST)){
                                oppNumber += 1;
                            }
                        }
                    }
                    reportDTO.setValue(oppNumber);
                    reportOutput.add(reportDTO);
                }
                break;

            //Get number of opportunities with OPEN status by city
            case REPORT_OPEN_BY_CITY:
                Map<String, List<AccountDTO>> oppsOByCity =
                        accounts.stream().collect(Collectors.groupingBy(w -> w.getCity()));
                for (String key : oppsOByCity.keySet()) {
                    List<AccountDTO> value = oppsOByCity.get(key);
                    ReportDTO reportDTO = new ReportDTO();
                    reportDTO.setLabel(key);
                    int oppNumber = 0;
                    for (AccountDTO accountDTO : value) {
                        List<Long> oppsByAcc = accountDTO.getOpportunities();
                        for(Long oppId: oppsByAcc){
                            if(opportunityServiceProxy.getById(oppId).getStatus().equals(Status.OPEN)){
                                oppNumber += 1;
                            }
                        }
                    }
                    reportDTO.setValue(oppNumber);
                    reportOutput.add(reportDTO);
                }
                break;

            //Get number of opportunities with by industry
            case REPORT_OPP_BY_INDUSTRY:
                Map<String, List<AccountDTO>> oppsByIndustry =
                        accounts.stream().collect(Collectors.groupingBy(w -> w.getIndustry()));
                for (String key : oppsByIndustry.keySet()) {
                    List<AccountDTO> value = oppsByIndustry.get(key);
                    ReportDTO reportDTO = new ReportDTO();
                    reportDTO.setLabel(key);
                    int oppNumber = 0;
                    for (AccountDTO accountDTO : value) {
                        oppNumber += accountDTO.getOpportunities().size();
                    }
                    reportDTO.setValue(oppNumber);
                    reportOutput.add(reportDTO);
                }
                break;

            //Get number of opportunities with CLOSE-WON status by industry
            case REPORT_CLOSE_W_BY_INDUSTRY:
                Map<String, List<AccountDTO>> oppsWByIndustry =
                        accounts.stream().collect(Collectors.groupingBy(w -> w.getIndustry()));
                for (String key : oppsWByIndustry.keySet()) {
                    List<AccountDTO> value = oppsWByIndustry.get(key);
                    ReportDTO reportDTO = new ReportDTO();
                    reportDTO.setLabel(key);
                    int oppNumber = 0;
                    for (AccountDTO accountDTO : value) {
                        List<Long> oppsByAcc = accountDTO.getOpportunities();
                        for(Long oppId: oppsByAcc){
                            if(opportunityServiceProxy.getById(oppId).getStatus().equals(Status.CLOSED_WON)){
                                oppNumber += 1;
                            }
                        }
                    }
                    reportDTO.setValue(oppNumber);
                    reportOutput.add(reportDTO);
                }
                break;

            //Get number of opportunities with CLOSE-LOST status by industry
            case REPORT_CLOSE_L_BY_INDUSTRY:
                Map<String, List<AccountDTO>> oppsLByIndustry =
                        accounts.stream().collect(Collectors.groupingBy(w -> w.getIndustry()));
                for (String key : oppsLByIndustry.keySet()) {
                    List<AccountDTO> value = oppsLByIndustry.get(key);
                    ReportDTO reportDTO = new ReportDTO();
                    reportDTO.setLabel(key);
                    int oppNumber = 0;
                    for (AccountDTO accountDTO : value) {
                        List<Long> oppsByAcc = accountDTO.getOpportunities();
                        for(Long oppId: oppsByAcc){
                            if(opportunityServiceProxy.getById(oppId).getStatus().equals(Status.CLOSED_LOST)){
                                oppNumber += 1;
                            }
                        }
                    }
                    reportDTO.setValue(oppNumber);
                    reportOutput.add(reportDTO);
                }
                break;

            //Get number of opportunities with OPEN status by industry
            case REPORT_OPEN_BY_INDUSTRY:
                Map<String, List<AccountDTO>> oppsOByIndustry =
                        accounts.stream().collect(Collectors.groupingBy(w -> w.getIndustry()));
                for (String key : oppsOByIndustry.keySet()) {
                    List<AccountDTO> value = oppsOByIndustry.get(key);
                    ReportDTO reportDTO = new ReportDTO();
                    reportDTO.setLabel(key);
                    int oppNumber = 0;
                    for (AccountDTO accountDTO : value) {
                        List<Long> oppsByAcc = accountDTO.getOpportunities();
                        for(Long oppId: oppsByAcc){
                            if(opportunityServiceProxy.getById(oppId).getStatus().equals(Status.OPEN)){
                                oppNumber += 1;
                            }
                        }
                    }
                    reportDTO.setValue(oppNumber);
                    reportOutput.add(reportDTO);
                }
                break;

            //Get average value of employees
            case MEAN_EMPCOUNT:
                ReportDTO reportDto = new ReportDTO();
                List<Integer> impMean = new ArrayList<>();
                for (AccountDTO accountDTO : accounts){
                    impMean.add(accountDTO.getEmployeeCount());
                }
                reportDto.setLabel("The average number of employees is: ");
                reportDto.setValue(getAvg(impMean));
                reportOutput.add(reportDto);
                break;

            //Get median value of employees
            case MEDIAN_EMPCOUNT:
                ReportDTO reportDTO = new ReportDTO();
                List<Integer> impCount = new ArrayList<>();
                for (AccountDTO accountDTO : accounts){
                    impCount.add(accountDTO.getEmployeeCount());
                }
                reportDTO.setLabel("The median number of employees is: ");
                reportDTO.setValue(getMedian(impCount));
                reportOutput.add(reportDTO);
                break;

            //Get maximum value of employees
            case MAX_EMPCOUNT:
                ReportDTO reportDTO1 = new ReportDTO();
                List<Integer> impMax = new ArrayList<>();
                for (AccountDTO accountDTO : accounts){
                    impMax.add(accountDTO.getEmployeeCount());
                }
                reportDTO1.setLabel("The maximum number of employees is: ");
                reportDTO1.setValue(getMax(impMax));
                reportOutput.add(reportDTO1);
                break;

            //Get minimum value of employees
            case MIN_EMPCOUNT:
                ReportDTO reportDTO2 = new ReportDTO();
                List<Integer> impMin = new ArrayList<>();
                for (AccountDTO accountDTO : accounts){
                    impMin.add(accountDTO.getEmployeeCount());
                }
                reportDTO2.setLabel("The minimum number of employees is: ");
                reportDTO2.setValue(getMin(impMin));
                reportOutput.add(reportDTO2);
                break;

            //Get average value of products
            case MEAN_QUANT:
                ReportDTO reportDTO3 = new ReportDTO();
                List<Integer> prodMean = new ArrayList<>();
                for (OpportunityDTO opp: opportunities){
                    prodMean.add(opp.getQuantity());
                }
                reportDTO3.setLabel("The average quantity is: ");
                reportDTO3.setValue(getAvg(prodMean));
                reportOutput.add(reportDTO3);
                break;

            //Get median value of products
            case MED_QUANT:
                ReportDTO reportDTO4 = new ReportDTO();
                List<Integer> prodCount = new ArrayList<>();
                for (OpportunityDTO opp: opportunities){
                    prodCount.add(opp.getQuantity());
                }
                reportDTO4.setLabel("The median quantity is: ");
                reportDTO4.setValue(getMedian(prodCount));
                reportOutput.add(reportDTO4);
                break;

            //Get maximum value of products
            case MAX_QUANT:
                ReportDTO reportDTO5 = new ReportDTO();
                List<Integer> prodMax = new ArrayList<>();
                for (OpportunityDTO opp: opportunities){
                    prodMax.add(opp.getQuantity());
                }
                reportDTO5.setLabel("The maximum quantity is: ");
                reportDTO5.setValue(getMax(prodMax));
                reportOutput.add(reportDTO5);
                break;

            //Get minimum value of products
            case MIN_QUANT:
                ReportDTO reportDTO6 = new ReportDTO();
                List<Integer> prodMin = new ArrayList<>();
                for (OpportunityDTO opp: opportunities){
                    prodMin.add(opp.getQuantity());
                }
                reportDTO6.setLabel("The minimum quantity is: ");
                reportDTO6.setValue(getMin(prodMin));
                reportOutput.add(reportDTO6);
                break;

            //Get average number of opportunities per account
            case MEAN_OPPS_PERR_ACC:
                ReportDTO reportDTO7 = new ReportDTO();
                List<Integer> meanOpps = new ArrayList<>();
                for (AccountDTO accountDTO : accounts){
                    meanOpps.add(accountDTO.getOpportunities().size());
                }
                reportDTO7.setLabel("The average number of opportunities per account is: ");
                reportDTO7.setValue(getAvg(meanOpps));
                reportOutput.add(reportDTO7);
                break;

            //Get median number of opportunities per account
            case MED_OPPS_PERR_ACC:
                ReportDTO reportDTO8 = new ReportDTO();
                List<Integer> medOpps = new ArrayList<>();
                for (AccountDTO accountDTO : accounts){
                    medOpps.add(accountDTO.getOpportunities().size());
                }
                reportDTO8.setLabel("The median number of opportunities per account is: ");
                reportDTO8.setValue(getMedian(medOpps));
                reportOutput.add(reportDTO8);
                break;

            //Get maximum number of opportunities per account
            case MAX_OPPS_PERR_ACC:
                ReportDTO reportDTO9 = new ReportDTO();
                List<Integer> maxOpps = new ArrayList<>();
                for (AccountDTO accountDTO : accounts){
                    maxOpps.add(accountDTO.getOpportunities().size());
                }
                reportDTO9.setLabel("The maximum number of opportunities per account is: ");
                reportDTO9.setValue(getMax(maxOpps));
                reportOutput.add(reportDTO9);
                break;

            //Get minimum number of opportunities per account
            case MIN_OPPS_PERR_ACC:
                ReportDTO reportDTO10 = new ReportDTO();
                List<Integer> minOpps = new ArrayList<>();
                for (AccountDTO accountDTO : accounts){
                    minOpps.add(accountDTO.getOpportunities().size());
                }
                reportDTO10.setLabel("The minimum number of opportunities per account is: ");
                reportDTO10.setValue(getMin(minOpps));
                reportOutput.add(reportDTO10);
                break;
            default:
                ReportDTO reportDTOd = new ReportDTO();
                reportDTOd.setLabel("The answer to everything is: ");
                reportDTOd.setValue(42);
                reportOutput.add(reportDTOd);
        }
        return reportOutput;
    }

    //**** UTIL METHODS ****

    //method to find median in an array
    public double getMedian(List<Integer> medianNums) {
        List<Integer> sortedList = medianNums.stream().sorted().collect(Collectors.toList());
        if (medianNums.size() % 2 == 1)
            return sortedList.get((medianNums.size() + 1) / 2 - 1).doubleValue();
        else {
            double lower = sortedList.get(medianNums.size() / 2 - 1).doubleValue();
            double upper = sortedList.get(medianNums.size() / 2).doubleValue();

            return (lower + upper) / 2.0;
        }
    }

    //method to find maximum in an array
    public int getMax(List<Integer> nums){
        return Collections.max(nums);
    }

    //method to find minimum in an array
    public double getMin(List<Integer> nums){
        return Collections.min(nums);
    }

    //method to find average in an array
    public double getAvg(List<Integer> nums){
        Integer sum = 0;
        for(Integer i: nums) {
            sum += i;
        }
        return sum.doubleValue()/ nums.size();
    }

}


