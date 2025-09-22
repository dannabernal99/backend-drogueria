package com.drogueria.drogueria.Roles;

import com.drogueria.drogueria.Roles.servicio.RolesServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RolesController {

    private final RolesServicio rolesService;

    @PostMapping
    public ResponseEntity<RolesDTO> crearRol(@RequestBody RolesDTO dto) {
        return ResponseEntity.ok(rolesService.crearRol(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolesDTO> obtenerRol(@PathVariable Long id) {
        return ResponseEntity.ok(rolesService.obtenerRolPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<RolesDTO>> listarRoles() {
        return ResponseEntity.ok(rolesService.listarRoles());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolesDTO> actualizarRol(@PathVariable Long id, @RequestBody RolesDTO dto) {
        return ResponseEntity.ok(rolesService.actualizarRol(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarRol(@PathVariable Long id) {
        rolesService.eliminarRol(id);
        return ResponseEntity.noContent().build();
    }
}
