package com.springframework.petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/owner")
public class OwnerController {
    @RequestMapping({"/","","/index"})
    String index(){
        return "/owner/index";
    }

}
