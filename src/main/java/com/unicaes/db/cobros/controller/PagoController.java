package com.unicaes.db.cobros.controller;

import com.unicaes.db.cobros.enity.Pago;
import com.unicaes.db.cobros.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/pago")
public class PagoController {
    @Autowired
    PagoRepository repository;

    @GetMapping("")
    public String listarPagos(Model model){
        List<Pago> pagos = (List<Pago>) repository.findAll();
        model.addAttribute("pagos", pagos);
        return "pago";
    }

    @PostMapping("/crear")
    public String crearPago(
            @RequestParam(value = "nombre") String nombre,
            @RequestParam(value = "detalles") String detalles
    ){

        Pago pago = new Pago();
        pago.setMetodoDePago(nombre);
        pago.setDetallesEspecificos(detalles);
        repository.save(pago);
        return "redirect:/pago";
    }

    @RequestMapping("/eliminar/{id}")
    public String eliminarPago(
            @PathVariable(value = "id") int id
    ){
        repository.deleteById(id);
        return "redirect:/pago";
}
}
