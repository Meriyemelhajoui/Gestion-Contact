package com.example.gestioncontact.Controllers;


import com.example.gestioncontact.DTO.ContactDTO;
import com.example.gestioncontact.Entities.Contact;
import com.example.gestioncontact.Entities.Mygroup;
import com.example.gestioncontact.Services.IContactServi;
import com.example.gestioncontact.Services.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ContactController {


    @Autowired
    IContactServi contactService;

    @Autowired
    IGroupService groupService;


    @GetMapping("/")
    public String home() {

        return "Home";
    }


    @GetMapping("contact/add")
    public String AddContact(Model model){
        model.addAttribute("contact", new Contact());
        model.addAttribute("groups", groupService.getAllGroups());
        return "addContact";
    }

    @GetMapping("/listContact")
    public String listContacts(Model model) {
        List<Contact> contacts = contactService.getAllContacts();
        for (Contact contact : contacts) {
            if (contact.getIdGroupe() != null) {
                Long groupId = contact.getIdGroupe();
                Optional<Mygroup> groupOptional = groupService.getGroupById(groupId);
                if (groupOptional.isPresent()) {
                    Mygroup group = groupOptional.get();
                    contact.setGroup(group);
                }
            }
        }
        model.addAttribute("contacts", contacts);
        return "listContact";
    }



    @PostMapping("EnregistrerContact")
    public String EnregistrerContact(@RequestParam("nom") String nom,
                                     @RequestParam("prenom") String prenom,
                                     @RequestParam("adresse") String adresse,
                                     @RequestParam("email_perso") String email_personnel,
                                     @RequestParam("email_pro") String email_professionel,
                                     @RequestParam("genre") String genre,
                                     @RequestParam("telephone_perso") String telephone_personnel,
                                     @RequestParam("telephone_pro") String telephone_professionnel,
                                     @RequestParam("groupId") Long groupId,
                                     Model model){


        // Verify if the user Already exists before Inserting //

        if(contactService.ContactExistOrNot(telephone_personnel,telephone_professionnel)){
            model.addAttribute("errorMessage", "A contact with the same phone number already exists");
            return "Error";
        }
            // Create a new Contact object
            Contact contact = new Contact();
            contact.setNom(nom);
            contact.setPrenom(prenom);
            contact.setAddresse(adresse);
            contact.setEmailPersonnel(email_personnel);
            contact.setEmailProfessionel(email_professionel);
            contact.setGenre(genre);
            contact.setTelephonePersonnel(telephone_personnel);
            contact.setTelephoneProfessionel(telephone_professionnel);
              if (groupId != null) {
            Optional<Mygroup> groupe = groupService.getGroupById(groupId);
            if(groupe.isPresent()){
                Mygroup group = groupe.get();
                contact.setGroup(group);}

             }


            // Save to the Database
            contactService.CreateContact(contact);

        // Ajoutez les groupes au modèle
        model.addAttribute("groups", groupService.getAllGroups());

            // Assuming the contact is successfully saved
            model.addAttribute("successMessage", "Contact saved successfully!");

        return "addContact";

    }


    @GetMapping("/contact/delete/{id}")
    public String deleteContact(@PathVariable("id") Long contactId , Model model){
        contactService.SupprimerContact(contactId);
        model.addAttribute("successMessage","Contact Deleted Successfully");
        return "redirect:/listContact";
    }



    // Modification des informations de contact //
    @GetMapping("/contact/edit/{id}")
    public String showEditForm(@PathVariable("id") Long contactId, Model model) {
        // Récupérer les informations du contact à partir du repository
        Optional<Contact> contact = contactService.getContactById(contactId);
        if (contact.isPresent()) {
            // Passer les informations du contact à la vue du formulaire de modification
            model.addAttribute("contact", contact.get());

            // Récupérer la liste des groupes disponibles
            List<Mygroup> groups = groupService.getAllGroups();
            model.addAttribute("groups", groups);
        }
        return "editForm";
    }



    @PostMapping("/contact/update")
    public String updateContact(@ModelAttribute("contact") ContactDTO updatedContact ,  @RequestParam("id") Long contactId , Model model) {
        // Récupérez l'objet `Contact` à partir du service en utilisant l'ID
        Optional<Contact> existingContact = contactService.getContactById(contactId);

        if (existingContact.isPresent()) {
            Contact contact = existingContact.get();

            // Mettez à jour les informations du contact en utilisant les valeurs du DTO
            contact.setNom(updatedContact.getNom());
            contact.setPrenom(updatedContact.getPrenom());
            contact.setAddresse(updatedContact.getAddresse());
            contact.setTelephonePersonnel(updatedContact.getTelephonePersonnel());
            contact.setTelephoneProfessionel(updatedContact.getTelephoneProfessionel());
            contact.setEmailPersonnel(updatedContact.getEmailPersonnel());
            contact.setEmailProfessionel(updatedContact.getEmailProfessionel());
            // Mettez à jour les autres propriétés selon vos besoins

            // Enregistrez les modifications dans la base de données
            contactService.EnregistrerContact(contact);
            //model.addAttribute("successMessage","Le contact a été bien mis à jour");

        }

        return "redirect:/listContact"; // Redirigez vers la page de liste des contacts
    }




    @GetMapping("/contact/search")
    public String searchContact(@RequestParam(value = "query", required = false) String query, Model model) {
        List<Contact> contacts = contactService.ChercherContact(query);

        if (!contacts.isEmpty()) {
            model.addAttribute("contacts", contacts);
            return "listContact";
        } else {
            model.addAttribute("errorMessage", "Aucun contact trouvé pour la recherche");
            return "Error";
        }
    }






}
