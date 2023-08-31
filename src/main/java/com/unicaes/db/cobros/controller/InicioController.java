package com.unicaes.db.cobros.controller;

import com.unicaes.db.cobros.enity.DetallesPromocion;
import com.unicaes.db.cobros.enity.Producto;
import com.unicaes.db.cobros.repository.ProductoRepository;
import com.unicaes.db.cobros.repository.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class InicioController {
    Producto producto;
    DetallesPromocion detallesPromocion;
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    PromocionRepository promocionRepository;


    @RequestMapping()
    public String inicio(){

        producto = new Producto();
        producto.setNombre("Jabon tex");
        producto.setCodigo("TX001");
        producto.setDescripcion("Jabon lavatrastos");
        producto.setPrecioUnitario(1.0);
        productoRepository.save(producto);

        detallesPromocion = new DetallesPromocion();
        detallesPromocion.setNombre("10% de descuento");
        detallesPromocion.setDescuentoAplicado(10.0);
        promocionRepository.save(detallesPromocion);

        return "inicio";
    }
}
