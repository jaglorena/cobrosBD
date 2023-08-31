package com.unicaes.db.cobros.enity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tarjeta")
@Data
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarjeta")
    private Integer idTarjeta;

    @Column(name = "saldo_actual")
    private Double saldoActual;

    @Column(name = "puntos_acumulados")
    private Double puntosAcumulados;

}
