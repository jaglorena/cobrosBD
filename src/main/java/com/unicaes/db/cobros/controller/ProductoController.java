package com.unicaes.db.cobros.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/producto")
public class ProductoController {
    @GetMapping("/producto")
    public String Productos(){
        return"producto";

    }
}
