package com.example.gestioncontact.Repositories;

import com.example.gestioncontact.Entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {


    // Find the Contact List with a specified Group id
    List<Contact> findAllByGroupe_Id(Long group_id);

    // To Show contact in an order way (Alphabetique order )
    List <Contact> findAllByOrderByNom();

    // Chercher contact par nom or Numero (Combianison de deux methodes )   //
    // Personnaliser une querie de selection  //

    @Query("SELECT c FROM Contact c WHERE LOWER(c.nom) LIKE %:query% OR c.telephonePersonnel LIKE %:query% OR c.telephoneProfessionel LIKE %:query%")
    List<Contact> searchContact(@Param("query") String query);





    // To verify if the Phone number (Professionel or Personnel already Exists //
    Boolean existsByTelephonePersonnelOrTelephoneProfessionel(String telephonePersonnel, String telephoneProfessionnel);


    // delete contact by his id

    public void deleteByid(long id);


    // Contact can be or can t be //
    public Optional<Contact> getContactById(Long id);

    // enregistrer Contact
    public Contact save(Contact contact);


}
