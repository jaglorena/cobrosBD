package com.unicaes.db.cobros.controller;


import com.unicaes.db.cobros.enity.Cliente;
import com.unicaes.db.cobros.enity.Tarjeta;
import com.unicaes.db.cobros.repository.ClienteRepository;
import com.unicaes.db.cobros.repository.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/tarjeta")
public class TarjetaController {
    @Autowired
    TarjetaRepository repository;
    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("")
    public String tarjeta(
            Model model
    ){
        List<Tarjeta> tarjetas = (List<Tarjeta>) repository.findAll();
        model.addAttribute("tarjetas", tarjetas);

        return"tarjeta";
    }

    @PostMapping("/crear")
    public String crearTarjeta(
        @RequestParam(value = "saldo") Double saldo,
        @RequestParam(value = "puntos") Double puntos,
        @RequestParam(value = "idCliente", defaultValue = "0") int idCliente
    ) {
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        if(cliente.isPresent()){
            Cliente cl = cliente.get();
            Tarjeta tarjeta = new Tarjeta();
            tarjeta.setSaldoActual(saldo);
            tarjeta.setPuntosAcumulados(puntos);
            repository.save(tarjeta);

            cl.setTarjeta(tarjeta);
            clienteRepository.save(cl);
        }

        return "redirect:/tarjeta";
    }
}
