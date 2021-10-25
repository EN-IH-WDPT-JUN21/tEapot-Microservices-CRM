package com.Ironhack.SalesRepService.controller;

import com.Ironhack.SalesRepService.dao.SalesRep;
import com.Ironhack.SalesRepService.dto.SalesRepDTO;
import com.Ironhack.SalesRepService.service.SalesRepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salesrep")
public class SalesRepController {
    @Autowired
    SalesRepService salesRepService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SalesRep getById(@PathVariable("id") Long id) {
        return salesRepService.getSalesRepDTO(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SalesRep> getAll() {
        return salesRepService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SalesRep create(@RequestBody SalesRepDTO transaction) {
        return salesRepService.create(transaction);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        salesRepService.delete(id);
    }
}
