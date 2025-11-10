package com.drogueria.drogueria.Compras;

import com.drogueria.drogueria.Compras.servicios.CompraServicio;
import com.drogueria.drogueria.Exception.UsuarioNoEncontradoException;
import com.drogueria.drogueria.SecurityConfig.JwtUtil;
import com.drogueria.drogueria.Usuarios.UsuarioRepositorio;
import com.drogueria.drogueria.Usuarios.Usuarios;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/compras")
@CrossOrigin(origins = "*")
public class CompraController {

    private final CompraServicio compraServicio;
    private final JwtUtil jwtUtil;
    private final UsuarioRepositorio usuarioRepositorio;

    public CompraController(CompraServicio compraServicio,
                            JwtUtil jwtUtil,
                            UsuarioRepositorio usuarioRepositorio) {
        this.compraServicio = compraServicio;
        this.jwtUtil = jwtUtil;
        this.usuarioRepositorio = usuarioRepositorio;
    }

    @PostMapping("/realizar-compra")
    public ResponseEntity<?> realizarCompra(@RequestBody CompraRequestDTO request) {
        try {
            if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
                return ResponseEntity
                        .badRequest()
                        .body(createErrorResponse("El username es requerido"));
            }

            CompraDTO compra = compraServicio.realizarCompraPorUsername(request.getUsername(), request);
            return ResponseEntity.ok(compra);

        } catch (UsuarioNoEncontradoException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(createErrorResponse("Usuario no encontrado: " + request.getUsername()));

        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(createErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/mis-compras")
    public ResponseEntity<List<CompraDTO>> obtenerMisCompras(Authentication authentication) {
        try {
            String username = authentication.getName();

            Usuarios usuario = usuarioRepositorio.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            List<CompraDTO> compras = compraServicio.obtenerComprasPorUsuario(usuario.getId());
            return ResponseEntity.ok(compras);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CompraDTO>> obtenerTodasLasCompras(Authentication authentication) {
        try {
            if (authentication == null) {
                return ResponseEntity.status(401).build();
            }

            List<CompraDTO> compras = compraServicio.obtenerTodasLasCompras();
            return ResponseEntity.ok(compras);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private Map<String, String> createErrorResponse(String message) {
        Map<String, String> error = new HashMap<>();
        error.put("error", message);
        return error;
    }}
