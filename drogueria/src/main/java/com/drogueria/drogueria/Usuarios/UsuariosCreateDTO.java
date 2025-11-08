package com.drogueria.drogueria.Usuarios;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuariosCreateDTO {
    private String username;
    private String password;
    private String email;
    private String nombreCompleto;
    private String telefono;
    private String roleNombre;
}
