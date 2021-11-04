package com.Ironhack.SalesRepService.controller;

import com.Ironhack.SalesRepService.dto.Create;
import com.Ironhack.SalesRepService.dto.SalesRepDTO;
import com.Ironhack.SalesRepService.dto.TransactionDTO;
import com.Ironhack.SalesRepService.dto.Update;
import com.Ironhack.SalesRepService.service.SalesRepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/crm/salesrep")
public class SalesRepController {
    @Autowired
    SalesRepService salesRepService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SalesRepDTO getById(@PathVariable("id") Long id) {
        return salesRepService.getSalesRepDTO(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SalesRepDTO> getAll() {
        return salesRepService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SalesRepDTO create(@RequestBody @Validated(Create.class) SalesRepDTO salesRepDTO){
        return salesRepService.create(salesRepDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        salesRepService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SalesRepDTO update(@PathVariable Long id, @RequestBody @Validated(Update.class) TransactionDTO transaction) {
        return salesRepService.update(id, transaction);
    }
}
