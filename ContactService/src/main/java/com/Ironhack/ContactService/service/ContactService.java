package com.Ironhack.ContactService.service;

import com.Ironhack.ContactService.dao.Contact;
import com.Ironhack.ContactService.dto.ContactDTO;
import com.Ironhack.ContactService.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    private ModelMapper modelMapper;



    public ContactDTO getDTO(Long id) {
        if(contactRepository.findById(id).isEmpty()){
            return null;
        }else{
            Contact contact=contactRepository.findById(id).get();
            return convertToDto(contact);
        }
    }

    public List<ContactDTO> getAll() {
        List<Contact> contacts = contactRepository.findAll();
        return contacts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ContactDTO create(ContactDTO transaction) throws ParseException {
        Contact contact=convertToEntity(transaction);
        contact=contactRepository.save(contact);
        return convertToDto(contact);
    }

    public ContactDTO update(Long id, ContactDTO transaction) {
        if(contactRepository.findById(id).isEmpty()){
            return null;
        }
        Contact contact=contactRepository.findById(id).get();

        if(transaction.getName()!=null && !transaction.getName().isEmpty()){
            contact.setName(transaction.getName());
        }
        if(transaction.getCompanyName()!=null && !transaction.getCompanyName().isEmpty()){
            contact.setCompanyName(transaction.getCompanyName());
        }
        if(transaction.getEmail()!=null && !transaction.getEmail().isEmpty()){
            contact.setEmail(transaction.getEmail());
        }
        if(transaction.getPhoneNumber()!=null && !transaction.getPhoneNumber().isEmpty()){
            contact.setPhoneNumber(transaction.getPhoneNumber());
        }
        contact=contactRepository.save(contact);

        return convertToDto(contact);
    }

    public void delete(Long id) {
        if(contactRepository.findById(id).isPresent()){
            contactRepository.delete(contactRepository.findById(id).get());
        }
    }

    private ContactDTO convertToDto(Contact contact) {
        ContactDTO contactDTO = modelMapper.map(contact, ContactDTO.class);
        return contactDTO;
    }

    private Contact convertToEntity(ContactDTO postDto) throws ParseException {
        Contact contact = modelMapper.map(postDto, Contact.class);
        return contact;
    }
}
