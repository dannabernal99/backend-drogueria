package com.drogueria.drogueria.Compras;

import com.drogueria.drogueria.Compras.servicios.CompraServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/compras")
@CrossOrigin(origins = "*")
public class CompraController {

    private final CompraServicio compraServicio;

    public CompraController(CompraServicio compraServicio) {
        this.compraServicio = compraServicio;
    }

    @PostMapping
    public ResponseEntity<CompraDTO> realizarCompra(
            @RequestBody CompraRequestDTO request,
            Authentication authentication) {

        Long usuarioId = obtenerUsuarioIdDelToken(authentication);

        try {
            CompraDTO compra = compraServicio.realizarCompra(usuarioId, request);
            return ResponseEntity.ok(compra);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/mis-compras")
    public ResponseEntity<List<CompraDTO>> obtenerMisCompras(Authentication authentication) {
        Long usuarioId = obtenerUsuarioIdDelToken(authentication);
        List<CompraDTO> compras = compraServicio.obtenerComprasPorUsuario(usuarioId);
        return ResponseEntity.ok(compras);
    }

    @GetMapping
    public ResponseEntity<List<CompraDTO>> obtenerTodasLasCompras() {
        List<CompraDTO> compras = compraServicio.obtenerTodasLasCompras();
        return ResponseEntity.ok(compras);
    }

    private Long obtenerUsuarioIdDelToken(Authentication authentication) {
        return 1L;
    }
}
