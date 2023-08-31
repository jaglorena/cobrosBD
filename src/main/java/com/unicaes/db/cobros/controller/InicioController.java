package com.unicaes.db.cobros.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class InicioController {

    @RequestMapping()
    public String inicio(){
        return "inicio";
    }
}
