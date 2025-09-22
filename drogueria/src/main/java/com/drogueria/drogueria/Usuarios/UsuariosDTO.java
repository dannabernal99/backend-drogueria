package com.drogueria.drogueria.Usuarios;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuariosDTO {
    private Long id;
    private String username;
    private String email;
    private String nombreCompleto;
    private String telefono;
    private Boolean activo;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private Long roleId;
    private String roleNombre;
}
