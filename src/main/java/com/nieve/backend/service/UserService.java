package com.nieve.backend.service;



import com.nieve.backend.model.user;
import com.nieve.backend.repository.userRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    private final userRepository repo;

    public UserService(userRepository repo) {
        this.repo = repo;
    }

    public user crearUsuario(String correo, String password, String rol, String rut) {
        String hash = new BCryptPasswordEncoder().encode(password);
        user u = new user();
        u.setCorreo(correo);
        u.setPasswordHash(hash);
        u.setRol(rol);
        u.setRut(rut);
        u.setFechaRegistro(new Date());
        return repo.save(u);
    }
}

