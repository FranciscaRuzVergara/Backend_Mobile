package com.nieve.backend.repository;

import com.nieve.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByCorreo(String correo);
    }



