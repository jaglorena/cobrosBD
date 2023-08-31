package com.unicaes.db.cobros.enity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Transacciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaccion")
    private Integer idTransaccion;

    @Column(name = "fecha_hora")
    private LocalDateTime fechaHora;

    @Column(name = "cantidad_vendida")
    private Integer cantidadVendida;

    @ManyToOne
    @JoinColumn(name = "id_pago", referencedColumnName = "id_pago")
    private Pago formaPago;

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "id_detalle", referencedColumnName = "id_detalle")
    private DetallesPromocion promocion;
}
