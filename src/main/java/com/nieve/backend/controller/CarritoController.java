package com.nieve.backend.controller;

import com.nieve.backend.model.Carrito;
import com.nieve.backend.model.Producto;
import com.nieve.backend.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CarritoController {
    @Autowired
    private CarritoService carritoService;
    @GetMapping
    public ResponseEntity<List<Carrito>> listar() {
        List<Carrito> Carrito = carritoService.findAll();
        if (Carrito.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(Carrito);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Carrito> buscar(@PathVariable Long id) {
        try {
            Carrito carrito= carritoService.findById(id);
            return ResponseEntity.ok(carrito);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Carrito> register(@RequestBody Carrito carrito) {
        Carrito nuevoCarrito = carritoService.save(carrito);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCarrito);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrito> actualizar(@PathVariable Long id, @RequestBody Carrito carrito) {
        try {
            Carrito car = carritoService.findById(id);
            carritoService.save(carrito);
            return ResponseEntity.ok(carrito);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            carritoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch ( Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }

}
