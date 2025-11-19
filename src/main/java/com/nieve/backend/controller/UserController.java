package com.nieve.backend.controller;

import com.nieve.backend.model.user;
import com.nieve.backend.model.userDTO;
import com.nieve.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<user> register(@RequestBody userDTO dto) {
        user u = service.crearUsuario(
                dto.getCorreo(),
                dto.getPassword(),
                dto.getRol(),
                dto.getRut()
        );
        return ResponseEntity.ok(u);
    }
}
