package com.drogueria.drogueria.Usuarios.servicios;

import com.drogueria.drogueria.SecurityConfig.AuthRequest;
import com.drogueria.drogueria.SecurityConfig.AuthResponse;
import com.drogueria.drogueria.Roles.Roles;
import com.drogueria.drogueria.Roles.RolesRepositorio;
import com.drogueria.drogueria.Usuarios.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServicioImpl implements  UsuarioServicio {

    private final UsuarioRepositorio  usuarioRepositorio;
    private final RolesRepositorio rolesRepositorio;
    private final PasswordEncoder passwordEncoder;

    public UsuariosDTO crearUsuario(UsuariosCreateDTO dto) {
        if (usuarioRepositorio.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("Username ya existe");
        }
        if (usuarioRepositorio.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email ya existe");
        }

        String rolNombre = dto.getRoleNombre() != null ? dto.getRoleNombre().toUpperCase() : "USER";

        Roles rol = rolesRepositorio.findByNombre(rolNombre)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + rolNombre));

        String encoded = passwordEncoder.encode(dto.getPassword());
        Usuarios entity = UsuariosMapper.toEntity(dto, rol, encoded);

        Usuarios saved = usuarioRepositorio.save(entity);
        return UsuariosMapper.toDTO(saved);
    }

    @Override
    public UsuariosDTO actualizarUsuario(Long id, UsuariosDTO dto) {
        Usuarios usuario = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + id));

        if (dto.getNombreCompleto() != null) usuario.setNombreCompleto(dto.getNombreCompleto());
        if (dto.getEmail() != null) usuario.setEmail(dto.getEmail());
        if (dto.getTelefono() != null) usuario.setTelefono(dto.getTelefono());
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if (dto.getRoleNombre() != null) {
            Roles rol = rolesRepositorio.findByNombre(dto.getRoleNombre().toUpperCase())
                    .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + dto.getRoleNombre()));
            usuario.setRol(rol);
        }

        Usuarios actualizado = usuarioRepositorio.save(usuario);
        return UsuariosMapper.toDTO(actualizado);
    }

    @Override
    public void eliminarUsuario(Long id) {
        if (!usuarioRepositorio.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado: " + id);
        }
        usuarioRepositorio.deleteById(id);
    }

    @Override
    public UsuariosDTO obtenerPorUsername(String username) {
        Usuarios usuario = usuarioRepositorio.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));
        return UsuariosMapper.toDTO(usuario);
    }

    @Override
    public List<UsuariosDTO> obtenerTodosMenosActual(String usernameActual) {
        return usuarioRepositorio.findAll()
                .stream()
                .filter(u -> !u.getUsername().equals(usernameActual))
                .map(UsuariosMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        return null;
    }
}
