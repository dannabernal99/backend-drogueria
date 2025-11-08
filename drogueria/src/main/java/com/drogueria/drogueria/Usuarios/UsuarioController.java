package com.drogueria.drogueria.Usuarios;

import com.drogueria.drogueria.Productos.Producto;
import com.drogueria.drogueria.Productos.servicios.ProductoServicio;
import com.drogueria.drogueria.Usuarios.servicios.UsuarioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServicio usuarioServicio;

    @PostMapping
    public ResponseEntity<UsuariosDTO> crearUsuarioPorAdministrador(@RequestBody UsuariosCreateDTO dto) {
        UsuariosDTO nuevoUsuario = usuarioServicio.crearUsuario(dto);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuariosDTO>> obtenerUsuarios(Principal principal) {
        String usernameActual = principal.getName();
        List<UsuariosDTO> usuarios = usuarioServicio.obtenerTodosMenosActual(usernameActual);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UsuariosDTO> obtenerPorUsername(@PathVariable String username) {
        UsuariosDTO usuario = usuarioServicio.obtenerPorUsername(username);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuariosDTO> actualizarUsuario(
            @PathVariable Long id,
            @RequestBody UsuariosDTO dto) {

        UsuariosDTO actualizado = usuarioServicio.actualizarUsuario(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioServicio.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
