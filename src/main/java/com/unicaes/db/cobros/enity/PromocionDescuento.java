package com.unicaes.db.cobros.enity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "promocion_descuento")
public class PromocionDescuento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_promocion_descuento")
    private Integer idPromocionDescuento;

    @Column(name = "nombre_promocion", length = 30)
    private String nombrePromocion;

    @Column(name = "tipo_descuento", length = 30)
    private String tipoDescuento;

    @Column(name = "valor_descuento")
    private Double valorDescuento = 0.0;
}
