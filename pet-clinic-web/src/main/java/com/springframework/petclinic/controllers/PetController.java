package com.springframework.petclinic.controllers;


import com.springframework.petclinic.model.Owner;
import com.springframework.petclinic.model.Pet;
import com.springframework.petclinic.model.PetType;
import com.springframework.petclinic.services.OwnerService;
import com.springframework.petclinic.services.PetService;
import com.springframework.petclinic.services.PetTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RequestMapping("/owners/{ownerId}")
@Controller
public class PetController {

    @Autowired
    PetService petService;
    @Autowired
    PetTypeService petTypeService;
    @Autowired
    OwnerService ownerService;

    Logger logger =  LoggerFactory.getLogger(this.getClass());

    public PetController(PetService petService, PetTypeService petTypeService, OwnerService ownerService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }

    @ModelAttribute("owner")
    public Owner addOwner(@PathVariable Long ownerId){
        return ownerService.findById(ownerId);
    }

    @ModelAttribute("types")
    public Set<PetType> addPetTypes(){
        return petTypeService.findAll();
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner, Model model){
        logger.info("inside new Pet");
        Pet pet = new Pet();
        pet.setOwner(owner);
//        owner.setPets(pet);
        model.addAttribute("pet",pet);
        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/new")
    public String processCreationForm(@PathVariable Long ownerId, @Valid Pet pet){
        pet.setOwner(ownerService.findById(ownerId));
        petService.save(pet);
        return "redirect:/owners/"+ownerId;
    }

    @GetMapping("/pets/{id}/edit")
    public String initUpdateForm(@PathVariable Long ownerId, @PathVariable Long id, Model model){

        Pet pet = petService.findById(id);
//        Owner owner = ownerService.findById(ownerId);
        model.addAttribute("pet",pet);
        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/{id}/edit")
    public String processUpdateForm(@PathVariable Long ownerId, @PathVariable Long id, Pet pet, Model model){

        pet.setId(id);
        petService.save(pet);
        return "redirect:/owners/"+ownerId;
    }


}
