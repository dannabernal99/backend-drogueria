package com.drogueria.drogueria.Usuarios.servicios;

import com.drogueria.drogueria.SecurityConfig.AuthRequest;
import com.drogueria.drogueria.SecurityConfig.AuthResponse;
import com.drogueria.drogueria.Roles.Roles;
import com.drogueria.drogueria.Roles.RolesRepositorio;
import com.drogueria.drogueria.Usuarios.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioServicioImpl implements  UsuarioServicio {

    private final UsuarioRepositorio  usuarioRepositorio;
    private final RolesRepositorio rolesRepositorio;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuariosDTO crearUsuario(UsuariosCreateDTO dto) {
        if (usuarioRepositorio.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username ya existe");
        }
        if (usuarioRepositorio.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email ya existe");
        }

        Roles rol = rolesRepositorio.findById(1L)
                .orElseThrow(() -> new RuntimeException("Rol con id=1 no encontrado"));

        String encoded = passwordEncoder.encode(dto.getPassword());

        Usuarios entity = UsuariosMapper.toEntity(dto, rol, encoded);

        Usuarios saved = usuarioRepositorio.save(entity);

        return UsuariosMapper.toDTO(saved);
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        return null;
    }

    @Override
    public UsuariosDTO obtenerPorUsername(String username) {
        Usuarios usuario = usuarioRepositorio.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));
        return UsuariosMapper.toDTO(usuario);
    }
}
