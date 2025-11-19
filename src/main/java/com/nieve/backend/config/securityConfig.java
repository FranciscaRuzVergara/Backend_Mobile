package com.nieve.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class securityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Usuarios
                        .requestMatchers("/api/usuarios/register").permitAll()
                        // Carrito
                        .requestMatchers("/api/carrito/**").permitAll()
                        // Pedidos
                        .requestMatchers("/api/pedidos/**").permitAll()
                        // Producto
                        .requestMatchers("/api/producto/**").permitAll()
                        // El resto requiere autenticación
                        .anyRequest().authenticated()
                );
        return http.build();
    }
}
