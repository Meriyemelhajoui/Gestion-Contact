package com.example.gestioncontact.Services;

import com.example.gestioncontact.Entities.Mygroup;
import com.example.gestioncontact.Repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface IGroupService {



    public Mygroup createGroup(Mygroup group);

    public void deleteGroupById(Long id);

    public List<Mygroup> searchGroupByNom(String nom);
    public  List <Mygroup>getAllGroups();
    public Optional<Mygroup> getGroupById(Long id);
}
