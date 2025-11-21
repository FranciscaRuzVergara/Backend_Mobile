package com.nieve.backend.service;

import com.nieve.backend.model.Carrito;
import com.nieve.backend.model.CarritoId;
import com.nieve.backend.repository.CarritoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> findAll() {
        return carritoRepository.findAll();
    }

    public Optional<Carrito> findById(CarritoId id) {
        return carritoRepository.findById(id);
    }

    public Carrito save(Carrito carrito ) {
        return carritoRepository.save(carrito);
    }

    public void delete(CarritoId id) {
        carritoRepository.deleteById(id);
    }

}
