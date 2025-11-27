package com.nieve.backend.service;

import com.nieve.backend.model.User;
import com.nieve.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    // Crear usuario
    public User crearUsuario(String correo, String password,String nombres, String apellidos, String rol, String rut) {
        User u = new User();
        u.setCorreo(correo);
        u.setPasswordHash(password);
        u.setRol(rol);
        u.setRut(rut);
        u.setFechaRegistro(new Date());
        return repo.save(u);
    }

    // Listar todos los usuarios
    public List<User> findAll() {
        return repo.findAll();
    }

    // Buscar usuario por ID
    public Optional<User> findById(Long id) {
        return repo.findById(id);
    }
    // buscar usuarios por correo
    public User findByCorreo(String correo) {
        return repo.findByCorreo(correo);
    }


    // Actualizar usuario
    public Optional<User> update(Long id, User user) {
        return repo.findById(id).map(existing -> {
            existing.setCorreo(user.getCorreo());
            existing.setNombres(user.getNombres());
            existing.setApellidos(user.getApellidos());
            existing.setRol(user.getRol());
            existing.setRut(user.getRut());
            existing.setPasswordHash(user.getPasswordHash());
            return repo.save(existing);
        });
    }

    // Eliminar usuario
    public boolean delete(Long id) {
        return repo.findById(id).map(u -> {
            repo.delete(u);
            return true;
        }).orElse(false);
    }
}
