package com.drogueria.drogueria.Usuarios.servicios;

import com.drogueria.drogueria.SecurityConfig.AuthRequest;
import com.drogueria.drogueria.SecurityConfig.AuthResponse;
import com.drogueria.drogueria.Usuarios.UsuariosCreateDTO;
import com.drogueria.drogueria.Usuarios.UsuariosDTO;

public interface UsuarioServicio {
    UsuariosDTO crearUsuario(UsuariosCreateDTO dto);
    AuthResponse authenticate(AuthRequest request);
    UsuariosDTO obtenerPorUsername(String username);
}
