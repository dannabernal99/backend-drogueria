package com.drogueria.drogueria.Roles.servicio;

import com.drogueria.drogueria.Roles.Roles;
import com.drogueria.drogueria.Roles.RolesDTO;
import com.drogueria.drogueria.Roles.RolesMapper;
import com.drogueria.drogueria.Roles.RolesRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolesServicioImpl implements RolesServicio {

    private final RolesRepositorio rolesRepositorio;

    @Override
    public RolesDTO crearRol(RolesDTO dto) {
        if (rolesRepositorio.existsByNombre(dto.getNombre())) {
            throw new RuntimeException("El rol ya existe");
        }
        Roles rol = RolesMapper.toEntity(dto);
        return RolesMapper.toDTO(rolesRepositorio.save(rol));
    }

    @Override
    public RolesDTO obtenerRolPorId(Long id) {
        Roles rol = rolesRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        return RolesMapper.toDTO(rol);
    }

    @Override
    public List<RolesDTO> listarRoles() {
        return rolesRepositorio.findAll()
                .stream()
                .map(RolesMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RolesDTO actualizarRol(Long id, RolesDTO dto) {
        Roles rol = rolesRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        rol.setNombre(dto.getNombre());
        rol.setDescripcion(dto.getDescripcion());

        return RolesMapper.toDTO(rolesRepositorio.save(rol));
    }

    @Override
    public void eliminarRol(Long id) {
        if (!rolesRepositorio.existsById(id)) {
            throw new RuntimeException("Rol no encontrado");
        }
        rolesRepositorio.deleteById(id);
    }
}
