package com.unicaes.db.cobros.enity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(length = 30, nullable = false)
    private String codigo;

    @Column(length = 30)
    private String nombre;

    @Column(length = 50)
    private String descripcion;

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario = 0.0;
}
