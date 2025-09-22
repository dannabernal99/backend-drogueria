package com.drogueria.drogueria.Usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuarios, Long> {
    Optional<Usuarios> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
