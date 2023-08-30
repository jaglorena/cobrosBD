package com.unicaes.db.cobros.service;

import com.unicaes.db.cobros.enity.Producto;
import com.unicaes.db.cobros.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServicio {

    @Autowired
    private ProductRepository productRepository;

    public void crearProducto(){
        Producto producto = new Producto();

        producto.setCodigo("CODIGO");
        producto.setDescripcion("DESC");
        producto.setPrecioUnitario(12.50);

        productRepository.save(producto);
    }

}
