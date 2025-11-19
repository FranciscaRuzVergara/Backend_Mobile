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
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String passwordHash;

    @Column(nullable = false)
    private String rol;

    @Column
    private String rut;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

}
