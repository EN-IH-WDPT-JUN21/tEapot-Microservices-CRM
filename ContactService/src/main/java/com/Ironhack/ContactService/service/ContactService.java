package com.Ironhack.ContactService.service;

import com.Ironhack.ContactService.dao.Contact;
import com.Ironhack.ContactService.dto.ContactDTO;
import com.Ironhack.ContactService.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;


    public Contact getDTO(Long id) {
        if(contactRepository.findById(id).isEmpty()){
            return null;
        }else{
            return contactRepository.findById(id).get();
        }
    }

    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    public Contact create(ContactDTO transaction) {
        Contact contact=new Contact();
        contact.setName(transaction.getName());
        contact.setPhoneNumber(transaction.getPhoneNumber());
        contact.setEmail(transaction.getEmail());
        contact.setCompanyName(transaction.getCompanyName());
        return contactRepository.save(contact);
    }

    public Contact update(Long id, ContactDTO transaction) {
        if(contactRepository.findById(id).isEmpty()){
            return null;
        }
        Contact contact=contactRepository.findById(id).get();

        if(!transaction.getName().isEmpty()){
            contact.setName(transaction.getName());
        }
        if(!transaction.getCompanyName().isEmpty()){
            contact.setCompanyName(transaction.getCompanyName());
        }
        if(!transaction.getEmail().isEmpty()){
            contact.setEmail(transaction.getEmail());
        }
        if(!transaction.getPhoneNumber().isEmpty()){
            contact.setPhoneNumber(transaction.getPhoneNumber());
        }
        return contactRepository.save(contact);
    }

    public void delete(Long id) {
        if(contactRepository.findById(id).isPresent()){
            contactRepository.delete(contactRepository.findById(id).get());
        }
    }
}
