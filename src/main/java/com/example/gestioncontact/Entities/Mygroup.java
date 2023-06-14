package com.example.gestioncontact.Entities;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Mygroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long id;

    private String nom;


    // Un group est associe a plusieurs Contact cad un grp peut contenir plusieurs contact
    @OneToMany(mappedBy = "groupe", cascade = CascadeType.ALL)
    private List<Contact> contacts;



    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }


}
