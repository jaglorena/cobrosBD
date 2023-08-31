package com.unicaes.db.cobros.enity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "detalles_promocion")
@Data
public class DetallesPromocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer idDetalle;

    @Column
    private String nombre;

    @Column(name = "descuento_aplicado")
    private Double descuentoAplicacdo;

}
