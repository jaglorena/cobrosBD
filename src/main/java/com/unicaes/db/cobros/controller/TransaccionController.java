package com.unicaes.db.cobros.controller;

import com.unicaes.db.cobros.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping ("/transacciones")
public class TransaccionController {
    @Autowired
    TransaccionRepository repository;
    @GetMapping ("")
    public String listarTransaccion(){
        return "transaccion";

    }
}