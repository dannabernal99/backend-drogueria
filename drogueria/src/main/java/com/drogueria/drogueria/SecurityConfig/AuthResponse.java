package com.drogueria.drogueria.SecurityConfig;

import com.drogueria.drogueria.Usuarios.UsuariosDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {
    private String token;
    private UsuariosDTO usuario;
}
