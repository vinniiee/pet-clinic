package com.springframework.petclinic.controllers;

import com.springframework.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }


    @RequestMapping({"/","","/index"})
    String listOwner(Model model){
        model.addAttribute("owners",ownerService.findAll());
        return "/owners/index";
    }
    @RequestMapping("/find")
    String ownersFind(){
        return "notimplemented";
    }

}
