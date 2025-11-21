package com.nieve.backend.controller;

import com.nieve.backend.model.Carrito;
import com.nieve.backend.model.CarritoId;
import com.nieve.backend.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/carrito")
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

    @GetMapping("/{nroPedido}/{idProducto}")
    public ResponseEntity <Carrito> buscar(@PathVariable Long nroPedido, @PathVariable Long idProducto) {
        CarritoId id= new CarritoId(nroPedido,idProducto);
        Optional<Carrito> carrito = carritoService.findById(id);
        return carrito.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
        
    }

    @PostMapping
    public ResponseEntity<Carrito> register(@RequestBody Carrito carrito) {
        Carrito nuevoCarrito = carritoService.save(carrito);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCarrito);
    }

    @PutMapping("/{nroPedido}/{idProducto}")
    public ResponseEntity<Carrito> actualizar(@PathVariable Long nroPedido,@PathVariable Long idProducto, @RequestBody Carrito carrito) {
            CarritoId id = new CarritoId(nroPedido,idProducto);
            Optional<Carrito> car = carritoService.findById(id);
            if(car.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            carrito.setId(id);
            Carrito actualizado = carritoService.save(carrito);
            return ResponseEntity.ok(actualizado);
        
    }

    @DeleteMapping("/{nroPedido}/{idProducto}")
    public ResponseEntity<?> eliminar(@PathVariable Long nroPedido, @PathVariable Long idProducto) {
        CarritoId id = new CarritoId(nroPedido,idProducto);
        if(carritoService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        carritoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
