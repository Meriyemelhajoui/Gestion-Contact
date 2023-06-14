package com.example.gestioncontact.Repositories;

import com.example.gestioncontact.Entities.Mygroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Mygroup,Long> {
    // Search Group By Name   //


    public Mygroup save(Mygroup mygroup);
    @Query("SELECT g FROM Mygroup g WHERE LOWER(g.nom) LIKE %:nom%")
    List<Mygroup> searchGroupByNom(@Param("nom") String nom);

    public List<Mygroup> findAll();


    public void deleteById(Long id);

    // methode qui retourne un objet group a partir de son id

    public Optional<Mygroup>  findById(Long id);



}
