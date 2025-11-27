package com.nieve.backend.controller;

import com.nieve.backend.model.User;
import com.nieve.backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService service;

    // Listar todos los usuarios
    @GetMapping
    public ResponseEntity<List<User>> listar() {
        List<User> usuarios = service.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    // Buscar usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<User> buscar(@PathVariable Long id) {
        try {
            User user = service.findById(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Buscar por correo
    @GetMapping("/correo/{correo}")
    public ResponseEntity<User> buscarPorCorreo(@PathVariable String correo) {
        User u = service.findByCorreo(correo);
        if (u == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(u);
    }

    @PostMapping
    public ResponseEntity<User> register(@RequestBody User user){
        User nuevoUser = service.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUser);
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<User> actualizar(@PathVariable Long id, @RequestBody User user) {
        try {
            User u = service.findById(id);
            u.setId(id);
            u.setCorreo(user.getCorreo());
            u.setNombres(user.getNombres());
            u.setApellidos(user.getApellidos());
            u.setRol(user.getRol());
            u.setPasswordHash(user.getPasswordHash());
            User actualizo = service.save(u);
            return ResponseEntity.ok(actualizo);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
