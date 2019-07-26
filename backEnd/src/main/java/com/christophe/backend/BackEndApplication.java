package com.christophe.backend;

import com.christophe.backend.dao.ContactRepository;
import com.christophe.backend.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class BackEndApplication implements CommandLineRunner {

    @Autowired
    private ContactRepository contactRepository;

    public static void main(String[] args) {
        SpringApplication.run(BackEndApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //(String nom, String prenom, Date date, String email, String photo, String telephone, String motPasse, String sexe)
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        contactRepository.save(new Contact("COUCHY", "Christophe", df.parse("23/06/1981"), "christophe.couchy@gmail.com", "chris.jpg", "0120304050", "test", "H"));
        contactRepository.save(new Contact("Doranco", "Bill", df.parse("01/01/2009"), "doranco@doranco.fr", "doranco.jpg", "0155252800", "doranco", "H"));

        contactRepository.findAll().forEach(c ->{
            System.out.println(c.getNom());
        });
    }
}
