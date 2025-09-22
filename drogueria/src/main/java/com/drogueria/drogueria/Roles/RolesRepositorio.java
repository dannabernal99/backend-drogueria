package com.drogueria.drogueria.Roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepositorio extends JpaRepository<Roles, Long> {
    boolean existsByNombre(String nombre);
}
