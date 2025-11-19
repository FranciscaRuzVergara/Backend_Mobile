package com.nieve.backend.controller;

import com.nieve.backend.model.Producto;
import com.nieve.backend.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {
    @Autowired
    private  ProductoService productoService;
    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        List<Producto> tipoCursos = productoService.findAll();
        if (tipoCursos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tipoCursos);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Producto> buscar(@PathVariable Long id) {
        try {
            Producto producto= productoService.findById(id);
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Producto> register(@RequestBody Producto producto) {
            Producto nuevoProducto = productoService.save(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
            try {
                Producto pro = productoService.findById(id);
                productoService.save(producto);
                return ResponseEntity.ok(producto);
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
            }
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            productoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch ( Exception e ) {
            return ResponseEntity.notFound().build();
        }
    }
}


