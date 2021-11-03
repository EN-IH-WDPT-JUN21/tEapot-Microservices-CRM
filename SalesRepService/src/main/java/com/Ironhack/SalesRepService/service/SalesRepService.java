package com.Ironhack.SalesRepService.service;

import com.Ironhack.SalesRepService.dao.SalesRep;
import com.Ironhack.SalesRepService.dto.SalesRepDTO;
import com.Ironhack.SalesRepService.dto.TransactionDTO;
import com.Ironhack.SalesRepService.enums.Type;
import com.Ironhack.SalesRepService.repository.SalesRepRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesRepService {

    @Autowired
    SalesRepRepository salesRepRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<SalesRepDTO> getAll() {
        List<SalesRep> salesReps = salesRepRepository.findAll();
        return salesReps.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public SalesRepDTO create(TransactionDTO transactionDTO){
        SalesRep salesRep=convertToEntity(transactionDTO);
        //add opportunity if provided in transaction and absent in accountDTO
        if(salesRep.getOpportunities()==null) {
            salesRep.setOpportunities(new ArrayList<>());
        }
        if(transactionDTO.getOpportunityId()!=null) {
            if(!salesRep.getOpportunities().contains(transactionDTO.getOpportunityId())) {
                salesRep.getOpportunities().add(transactionDTO.getOpportunityId());
            }
        }
        //add contact if provided in transaction and absent in accountDTO
        if(salesRep.getLeads()==null){
            salesRep.setLeads(new ArrayList<>());
        }
        if(transactionDTO.getLeadId()!=null) {
            if (!salesRep.getLeads().contains(transactionDTO.getLeadId())) {
                salesRep.getLeads().add(transactionDTO.getLeadId());
            }
        }

        salesRep=salesRepRepository.save(salesRep);
        return convertToDto(salesRep);
    }

    public void delete(Long id) {
        if (salesRepRepository.findById(id).isPresent()) {
            salesRepRepository.delete(salesRepRepository.findById(id).get());
        }
    }

    public SalesRepDTO getSalesRepDTO(Long id) {
        if (salesRepRepository.findById(id).isEmpty()) {
            return null;
        } else {
            SalesRep salesRep=salesRepRepository.findById(id).get();
            return convertToDto(salesRep);
        }
    }

    public SalesRepDTO update(Long id, TransactionDTO transaction){
        if (salesRepRepository.findById(id).isEmpty()) {
            return null;
        } else{
            SalesRep salesRep=salesRepRepository.findById(id).get();
            if(transaction.getTransactionType().equals(Type.ADD)){
                if(!salesRep.getOpportunities().contains(transaction.getOpportunityId())){
                    salesRep.getOpportunities().add(transaction.getOpportunityId());
                }
                if(!salesRep.getLeads().contains(transaction.getLeadId())){
                    salesRep.getLeads().add(transaction.getLeadId());
                }
            }
            if(transaction.getTransactionType().equals(Type.REMOVE)){
                salesRep.getOpportunities().remove(transaction.getOpportunityId());
                salesRep.getLeads().remove(transaction.getLeadId());
            }
            salesRep=salesRepRepository.save(salesRep);
            return convertToDto(salesRep);
        }
    }


    public SalesRepDTO convertToDto(SalesRep salesRep) {
        return modelMapper.map(salesRep, SalesRepDTO.class);
    }

    public SalesRep convertToEntity(TransactionDTO transactionDTO){
        return modelMapper.map(transactionDTO, SalesRep.class);
    }

}
