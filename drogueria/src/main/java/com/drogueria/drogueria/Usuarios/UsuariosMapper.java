package com.drogueria.drogueria.Usuarios;

import com.drogueria.drogueria.Roles.Roles;

public class UsuariosMapper {
    public static UsuariosDTO toDTO(Usuarios u) {
        if (u == null) return null;
        return UsuariosDTO.builder()
                .id(u.getId())
                .username(u.getUsername())
                .email(u.getEmail())
                .nombreCompleto(u.getNombreCompleto())
                .telefono(u.getTelefono())
                .activo(u.getActivo())
                .fechaCreacion(u.getFechaCreacion())
                .fechaActualizacion(u.getFechaActualizacion())
                .roleId(u.getRol() != null ? u.getRol().getId() : null)
                .roleNombre(u.getRol() != null ? u.getRol().getNombre() : null)
                .build();
    }

    public static Usuarios toEntity(UsuariosCreateDTO dto, Roles rol, String encodedPassword) {
        if (dto == null) return null;
        return Usuarios.builder()
                .username(dto.getUsername())
                .password(encodedPassword)
                .email(dto.getEmail())
                .nombreCompleto(dto.getNombreCompleto())
                .telefono(dto.getTelefono())
                .activo(true)
                .rol(rol)
                .build();
    }
}