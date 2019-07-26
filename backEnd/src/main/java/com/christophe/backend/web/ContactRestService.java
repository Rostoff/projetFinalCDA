package com.christophe.backend.web;

import com.christophe.backend.dao.ContactRepository;
import com.christophe.backend.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactRestService {

    @Autowired
    public ContactRepository contactRepository;

    //Trouver toute la liste
    @RequestMapping(value="/contacts", method = RequestMethod.GET)
    public List<Contact> getContacts(){
        return contactRepository.findAll();
    }

    //Trouver un élément à la fois par ID
    @GetMapping(value="/contacts/{id}")
    public Contact getContactById(@PathVariable Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    //Ajouter un élément
    @PostMapping(value="/contacts")
    public Contact addContact(@RequestBody Contact c){
        return contactRepository.save(c);
    }

    //Supprimer un élément à la fois par ID
    @DeleteMapping(value="/contacts/{id}")
    public boolean deleteContactById(@PathVariable Long id){
        contactRepository.deleteById(id);
        return true;
    }

    //Modifier un élément
    @PutMapping(value="/contacts/{id}")
    public Contact modifyContact(@PathVariable Long id, @RequestBody Contact c){
        c.setId(id);
        return contactRepository.save(c);
    }

    //Chercher un élément par ID
    @GetMapping(value="/chercher")
    public Page<Contact> chercher(
            @RequestParam(name="mc", defaultValue = "") String mc,
            @RequestParam(name="page", defaultValue = "0") int page,
            @RequestParam(name="size", defaultValue = "5") int size) {
        return contactRepository.chercher("%"+mc+"%", PageRequest.of(page, size));
    }
}
