package com.example.gestioncontact.Controllers;

import com.example.gestioncontact.Entities.Mygroup;
import com.example.gestioncontact.Entities.Contact;
import com.example.gestioncontact.Services.GroupService;
import com.example.gestioncontact.Services.ContactService;
import com.example.gestioncontact.Services.IContactServi;
import com.example.gestioncontact.Services.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class GroupController {

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IContactServi contactService;

    @GetMapping("/listGroup")
    public String showGroupList(Model model) {
        List<Mygroup> groups = groupService.getAllGroups();
        model.addAttribute("groups", groups);
        return "listGroup";
    }

    @GetMapping("/addGroup")
    public String showAddGroupForm(Model model) {
        model.addAttribute("group", new Mygroup());
        return "addGroup";
    }

    @PostMapping("/addToGroups")
    public String addGroup(@ModelAttribute("group") Mygroup group) {
        groupService.createGroup(group);
        return "redirect:/listGroup";
    }





    @GetMapping("/delete/{id}")
    public String deleteGroup(@PathVariable("id") Long groupId, Model model) {
        System.out.println("hey from delete ");
        Optional<Mygroup> groupOptional = groupService.getGroupById(groupId);
        if (groupOptional.isPresent()) {
            Mygroup group = groupOptional.get();
            if (group.getContacts() != null && !group.getContacts().isEmpty()) {
                // Des contacts sont associés au groupe
                model.addAttribute("errorMessage", "Contacts are associated with this group. Delete the contacts first.");
                return "Error";
            }
            // Supprimer le groupe s'il n'y a pas de contacts associés
            groupService.deleteGroupById(groupId);
            model.addAttribute("successMessage", "Group Deleted Successfully");
            return "redirect:/listGroup";
        }
        // Le groupe n'existe pas
        model.addAttribute("errorMessage", "Group not found.");
        return "Error";
    }




    @GetMapping("/search")
    public String searchGroupByName(@RequestParam("nom") String nom, Model model) {
        List<Mygroup> groups = groupService.searchGroupByNom(nom);
        if(!groups.isEmpty()){  model.addAttribute("groups", groups);
            return "listGroup";}
            model.addAttribute("errorMessage","No group Found with this name");
            return "Error";

    }







}
