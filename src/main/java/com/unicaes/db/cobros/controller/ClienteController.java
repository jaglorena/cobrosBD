package com.unicaes.db.cobros.controller;

import com.unicaes.db.cobros.enity.Cliente;
import com.unicaes.db.cobros.repository.ClienteRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping ("/clientes")
public class ClienteController {
    @Autowired
    ClienteRepository repository;
    @GetMapping ("")
    public String listarCliente(Model model){
        List<Cliente> clientes = (List<Cliente>) repository.findAll();
        model.addAttribute("clientes", clientes);
        return "cliente";
    }

    @PostMapping("/crear")
    public String crearCliente(
            @RequestParam(value = "nombre") String nombre,
            @RequestParam(value = "direccion") String direccion
    ){
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setDireccion(direccion);
        repository.save(cliente);
        return "redirect:/clientes";
    }
}
