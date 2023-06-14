package com.example.gestioncontact.Services;


import com.example.gestioncontact.Entities.Contact;
import com.example.gestioncontact.Entities.Mygroup;
import com.example.gestioncontact.Repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService implements IGroupService {

    @Autowired
    GroupRepository groupRepository;

    // Create Group  //
    public Mygroup createGroup(Mygroup group) {
        return groupRepository.save(group);
    }
    // Delete Group
    public void deleteGroupById(Long id){
        groupRepository.deleteById(id);
    }

    // Search Group By name //
    public List<Mygroup> searchGroupByNom(String nom){
        return groupRepository.searchGroupByNom(nom);
    }

    // get all groups

    public  List <Mygroup>getAllGroups(){
        return groupRepository.findAll();
    }

    public Optional<Mygroup> getGroupById(Long id){
        return groupRepository.findById(id);
    }









}
