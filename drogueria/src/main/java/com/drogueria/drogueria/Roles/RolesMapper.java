package com.drogueria.drogueria.Roles;

import org.springframework.stereotype.Service;

@Service
public class RolesMapper {
    public static RolesDTO toDTO(Roles rol) {
        if (rol == null) return null;
        return RolesDTO.builder()
                .id(rol.getId())
                .nombre(rol.getNombre())
                .descripcion(rol.getDescripcion())
                .build();
    }

    public static Roles toEntity(RolesDTO dto) {
        if (dto == null) return null;
        return Roles.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();
    }
}
