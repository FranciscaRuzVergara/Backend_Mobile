package com.nieve.backend.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoId implements Serializable {
    
    @Column(name="nroPedido")
    private Long nroPedido;

    @Column(name="idProducto")
    private Long idProducto;
    
}
