package com.nieve.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nroPedido;

    @Column(nullable = false, unique = true)
    private String nombreProducto;

    @Column(nullable = false, unique = true)
    private Integer cantidad;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPedido;
}
