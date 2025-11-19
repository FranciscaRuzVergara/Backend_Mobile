package com.nieve.backend.repository;

import com.nieve.backend.model.user;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepository extends JpaRepository<user, Long> {

}

