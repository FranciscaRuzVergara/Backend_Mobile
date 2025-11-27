package com.nieve.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nroPedido;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_pedido_user")
    )
    @JsonIgnoreProperties({"pedidos"})
    private User user;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"pedido"})
    private Set<Carrito> carritos;
}
