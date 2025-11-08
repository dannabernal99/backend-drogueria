package com.drogueria.drogueria.Usuarios.servicios;

import com.drogueria.drogueria.SecurityConfig.AuthRequest;
import com.drogueria.drogueria.SecurityConfig.AuthResponse;
import com.drogueria.drogueria.Usuarios.UsuariosCreateDTO;
import com.drogueria.drogueria.Usuarios.UsuariosDTO;

import java.util.List;

public interface UsuarioServicio {
    UsuariosDTO crearUsuario(UsuariosCreateDTO dto);
    UsuariosDTO actualizarUsuario(Long id, UsuariosDTO dto);
    void eliminarUsuario(Long id);
    UsuariosDTO obtenerPorUsername(String username);
    List<UsuariosDTO> obtenerTodosMenosActual(String usernameActual);
    AuthResponse authenticate(AuthRequest request);
}
