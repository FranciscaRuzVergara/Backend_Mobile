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
    @EmbeddedId
    private CarritoId id;

    @Column(nullable = false)
    private Integer subtotal;

    @Column(nullable = false)
    private Integer cantidad;

    //Pedido
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("nroPedido")
    @JsonBackReference
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(
            name = "nroPedido",
            nullable = false,
            insertable = false,
            updatable = false,
            foreignKey = @ForeignKey(name = "fk_carrito_pedido")
    )
    private Pedido pedido;

    //Producto
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idProducto")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(
            name = "idProducto",
            nullable = false,
            insertable = false,
            updatable = false,
            foreignKey = @ForeignKey(name = "fk_carrito_producto")
    )
    private Producto producto;
}
