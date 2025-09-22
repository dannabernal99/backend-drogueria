package com.drogueria.drogueria.Roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepositorio extends JpaRepository<Roles, Long> {
    boolean existsByNombre(String nombre);
    Optional<Roles> findByNombre(String nombre);
}
