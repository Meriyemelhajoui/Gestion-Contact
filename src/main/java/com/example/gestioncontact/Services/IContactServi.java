package com.example.gestioncontact.Services;

import com.example.gestioncontact.Entities.Contact;

import java.util.List;
import java.util.Optional;

public interface IContactServi {
    public List<Contact> getAllContacts();
    public List<Contact>getAllOrderByNom();
    public void CreateContact(Contact contact);
    public void DeleteContactById(Long id);

    public Optional<Contact> getContactById(Long id );
    public List<Contact> getAllContactByGroupId(Long group_id);

    public List<Contact> ChercherContact(String query);

    public Boolean ContactExistOrNot(String telephonePersonnel, String telephoneProfessionnel);
    public void SupprimerContact(Long id);
    public Optional<Contact> getContactByid(Long id);
    public Contact EnregistrerContact(Contact contact);
}
