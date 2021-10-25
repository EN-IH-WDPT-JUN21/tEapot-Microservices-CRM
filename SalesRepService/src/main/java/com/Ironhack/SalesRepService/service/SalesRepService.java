package com.Ironhack.SalesRepService.service;

import com.Ironhack.SalesRepService.dao.SalesRep;
import com.Ironhack.SalesRepService.dto.SalesRepDTO;
import com.Ironhack.SalesRepService.repository.SalesRepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SalesRepService {

    @Autowired
    SalesRepRepository salesRepRepository;

    public List<SalesRep> getAll() {
        return salesRepRepository.findAll();
    }

    public SalesRep create(SalesRepDTO transaction) {
        SalesRep salesRep = new SalesRep();
        salesRep.setName(transaction.getName());
        return salesRepRepository.save(salesRep);
    }


    public void delete(Long id) {
        if (!salesRepRepository.findById(id).isEmpty()) {
            salesRepRepository.delete(salesRepRepository.findById(id).get());
        }
    }

    public SalesRep getSalesRepDTO(Long id) {
        if (salesRepRepository.findById(id).isEmpty()) {
            return null;
        } else {
            return salesRepRepository.findById(id).get();
        }
    }

}
