package com.nieve.backend.repository;

import com.nieve.backend.model.Carrito;
import com.nieve.backend.model.CarritoId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoRepository extends JpaRepository<Carrito, CarritoId> {
}
