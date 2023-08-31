package com.unicaes.db.cobros.controller;

import com.unicaes.db.cobros.enity.DetallesPromocion;
import com.unicaes.db.cobros.repository.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;
import java.util.List;

@Controller
@RequestMapping("/promociones")
public class PromocionController {
    @Autowired
    PromocionRepository repository;

    @GetMapping ("")
    public String listarPromocion(Model model){
        List<DetallesPromocion> promciones = (List<DetallesPromocion>) repository.findAll();
        model.addAttribute("promociones", promciones);
        return "promocion";
    }

    @PostMapping("/crear")
    public String crearPromocion(
            @RequestParam(value = "nombre") String nombre,
            @RequestParam(value = "porcentaje") Double porcentaje
    ){
        DetallesPromocion promocion = new DetallesPromocion();
        promocion.setNombre(nombre);
        promocion.setDescuentoAplicacdo(porcentaje);

        repository.save(promocion);

        return "redirect:/promociones";
    }
}


