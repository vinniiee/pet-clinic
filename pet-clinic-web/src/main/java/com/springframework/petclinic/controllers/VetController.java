package com.springframework.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vet")
public class VetController {
    @RequestMapping({"","/index","/"})
    String index(){
        return "vet/index";
    }


}
