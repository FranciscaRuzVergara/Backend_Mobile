package com.nieve.backend.controller;

import com.nieve.backend.model.User;
import com.nieve.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // Crear usuario (registro)
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User u = service.crearUsuario(
                user.getCorreo(),
                user.getPasswordHash(),
                user.getRol(),
                user.getRut()
        );
        return ResponseEntity.ok(u);
    }

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
        Optional<User> usuario = service.findById(id);
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<User> actualizar(@PathVariable Long id, @RequestBody User user) {
        Optional<User> actualizado = service.update(id, user);
        return actualizado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (service.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
