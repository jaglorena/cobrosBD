package com.unicaes.db.cobros.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tarjeta")
public class TarjetaController {
    @GetMapping("/tarjeta")
    public String tarjeta(){
        return"tarjeta";

    }
}
