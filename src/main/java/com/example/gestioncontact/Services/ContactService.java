package com.example.gestioncontact.Services;


import com.example.gestioncontact.Entities.Contact;
import com.example.gestioncontact.Repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService  implements IContactServi{

    @Autowired
    ContactRepository contactRepository;

    // Afficher tous les contacts
    public List<Contact> getAllContacts(){
        return contactRepository.findAll();
    }

    //Afficher les contacts selon un ordre alphabetique

    public List<Contact>getAllOrderByNom(){
        return contactRepository.findAllByOrderByNom();
    }


    // Creer un Contact
    public void CreateContact(Contact contact){
        contactRepository.save((contact));
    }


    // Supprimer un contact
    public void DeleteContactById(Long id){
        contactRepository.deleteById(id);
    }


    //Optional means that the contact can be or can't be , it will be handled
    public Optional<Contact> getContactById(Long id ){
        return contactRepository.findById(id);
    }

    // Recupere les contacts d'un groupe specifique
    public List<Contact> getAllContactByGroupId(Long group_id){
        return contactRepository.findAllByGroupe_Id(group_id);
    }



    public List<Contact> ChercherContact(String query) {
        return contactRepository.searchContact(query);
    }


    // Verify if the Contact already exists or not //
    public Boolean ContactExistOrNot(String telephonePersonnel, String telephoneProfessionnel){
         return contactRepository.existsByTelephonePersonnelOrTelephoneProfessionel(telephonePersonnel,telephoneProfessionnel);
    }

    public void SupprimerContact(Long id){
        contactRepository.deleteById(id);
    }

    public Optional<Contact> getContactByid(Long id){
        return  contactRepository.findById(id);

    }

    public Contact EnregistrerContact(Contact contact){
        return contactRepository.save(contact);
    }





}
