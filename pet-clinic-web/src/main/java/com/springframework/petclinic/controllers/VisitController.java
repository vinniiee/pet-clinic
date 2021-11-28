package com.springframework.petclinic.controllers;

import com.springframework.petclinic.model.Pet;
import com.springframework.petclinic.model.Visit;
import com.springframework.petclinic.services.PetService;
import com.springframework.petclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class VisitController {

    PetService petService;
    VisitService visitService;

    public VisitController(PetService petService, VisitService visitService) {
        this.petService = petService;
        this.visitService = visitService;
    }

    @ModelAttribute("visit")
    public Visit addVisit(@PathVariable Long petId, Map<String,Object> model){

        Pet pet = petService.findById(petId);
        model.put("pet",pet);
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);
        return  visit;
    }

    @GetMapping("/owners/*/pets/{petId}/visits/new")
    public String initVisitForm(){
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping("/owners/{ownerId}/pets/{petId}/visits/new")
    public String processVisitForm(@PathVariable String ownerId, Visit visit){
        visitService.save(visit);
        return "redirect:/owners/"+ownerId;
    }

}
