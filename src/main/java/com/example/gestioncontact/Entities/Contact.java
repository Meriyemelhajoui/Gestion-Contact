package com.example.gestioncontact.Entities;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Data
public class Contact {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String nom ;
    private String prenom ;
    private String telephonePersonnel ;
    private String telephoneProfessionel;
    private String Addresse;

    @Email
    private String emailPersonnel ;



    @Email
    @Column(unique = true)
    private  String emailProfessionel;

    private String genre;


    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "group_id")
    private Mygroup groupe;


   public void setIdGroup(long id){
       this.groupe.setId(id);
   }
   public void  setGroup(Mygroup group){
       this.groupe=group;
   }

    public Long getIdGroupe() {
        if (groupe != null) {
            return groupe.getId();
        }
        return null;
    }
}
