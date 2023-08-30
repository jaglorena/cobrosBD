package com.unicaes.db.cobros.enity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Integer idPago;

    @Column(name = "metodo_de_pago", length = 30)
    private String metodoDePago;

    @Column(name = "detalles_especificos", length = 50)
    private String detallesEspecificos;

}
