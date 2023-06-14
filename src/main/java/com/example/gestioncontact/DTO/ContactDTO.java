package com.example.gestioncontact.DTO;

import javax.validation.constraints.Email;

public class ContactDTO {
    private String nom ;
    private String prenom ;
    private String telephonePersonnel ;
    private String telephoneProfessionel;
    private String Addresse;

    private String emailPersonnel ;
    private String emailProfessionel ;

    private String genre;
//    private Long groupId; // New field for group ID
//    private String groupName; // New field for group name


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephonePersonnel() {
        return telephonePersonnel;
    }

    public void setTelephonePersonnel(String telephonePersonnel) {
        this.telephonePersonnel = telephonePersonnel;
    }

    public String getTelephoneProfessionel() {
        return telephoneProfessionel;
    }

    public void setTelephoneProfessionel(String telephoneProfessionel) {
        this.telephoneProfessionel = telephoneProfessionel;
    }

    public String getAddresse() {
        return Addresse;
    }

    public void setAddresse(String addresse) {
        Addresse = addresse;
    }

    public String getEmailPersonnel() {
        return emailPersonnel;
    }

    public void setEmailPersonnel(String emailPersonnel) {
        this.emailPersonnel = emailPersonnel;
    }


    public String getEmailProfessionel() {
        return emailProfessionel;
    }

    public void setEmailProfessionel(String emailProfessionel) {
        this.emailProfessionel = emailProfessionel;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }



}
