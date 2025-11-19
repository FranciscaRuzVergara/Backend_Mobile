package com.nieve.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carrito {

    @Column(nullable = false, unique = true)
    private Integer subtotal;

    @Column(nullable = false)
    private Integer cantidad;

    //Pedido
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(
            name = "nroPedido",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_carrito_pedido")
    )
    private Pedido pedido;

    //Producto
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(
            name = "idProducto",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_carrito_producto")
    )
    private Producto producto;
}
