package com.Ironhack.ContactService.controller;

import com.Ironhack.ContactService.dao.Contact;
import com.Ironhack.ContactService.dto.ContactDTO;
import com.Ironhack.ContactService.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Contact getById(@PathVariable("id") Long id) {
        return contactService.getDTO(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Contact> getAll() {
        return contactService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contact create(@RequestBody ContactDTO transaction) {
        return contactService.create(transaction);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Contact update(@PathVariable Long id, @RequestBody ContactDTO transaction) {
        return contactService.update(id, transaction);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        contactService.delete(id);
    }

}
