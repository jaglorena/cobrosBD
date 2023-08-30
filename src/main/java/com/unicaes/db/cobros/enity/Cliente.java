package com.unicaes.db.cobros.enity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Cliente {

    @Id
    @Column(name = "id_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @Column(length = 30, nullable = false)
    private String nombre;

    @Column(length = 50)
    private String direccion;

    @OneToOne
    @JoinColumn(name = "id_tarjeta", referencedColumnName = "id_tarjeta")
    private Tarjeta tarjeta;
}
