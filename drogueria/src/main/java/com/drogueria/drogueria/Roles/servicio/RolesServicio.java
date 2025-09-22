package com.drogueria.drogueria.Roles.servicio;

import com.drogueria.drogueria.Roles.RolesDTO;

import java.util.List;

public interface RolesServicio {
    RolesDTO crearRol(RolesDTO dto);
    RolesDTO obtenerRolPorId(Long id);
    List<RolesDTO> listarRoles();
    RolesDTO actualizarRol(Long id, RolesDTO dto);
    void eliminarRol(Long id);
}
