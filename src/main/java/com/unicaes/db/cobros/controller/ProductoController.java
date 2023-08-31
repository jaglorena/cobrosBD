package com.unicaes.db.cobros.controller;

import com.unicaes.db.cobros.enity.Producto;
import com.unicaes.db.cobros.repository.ProductRepository;
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
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    ProductRepository repository;

    @GetMapping("")
    public String productos(
            Model model
    ){
        List<Producto> productos = (List<Producto>) repository.findAll();
        model.addAttribute("productos", productos);

        return"producto";
    }

    @PostMapping("/crear")
    public String crearProducto(
            @RequestParam(value = "codigo") String codigo,
            @RequestParam(value = "nombre") String nombre,
            @RequestParam(value = "descripcion") String descripcion,
            @RequestParam(value = "precio") Double precio
    ) {
        Producto producto = new Producto();
        producto.setCodigo(codigo);
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecioUnitario(precio);

        repository.save(producto);

        return "redirect:/producto";
    }

    @RequestMapping("/eliminar/{id}")
    public String eliminarProducto(
            @PathVariable(value = "id") int id
    ){
        repository.deleteById(id);
        return "redirect:/producto";
}
}
