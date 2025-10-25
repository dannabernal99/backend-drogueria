package com.drogueria.drogueria.SecurityConfig;

import com.drogueria.drogueria.Usuarios.UsuariosCreateDTO;
import com.drogueria.drogueria.Usuarios.UsuariosDTO;
import com.drogueria.drogueria.Usuarios.servicios.UsuarioServicioImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioServicioImpl usuariosService;
    private final JwtUtil  jwtUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UsuariosDTO> register(@RequestBody UsuariosCreateDTO dto) {
        UsuariosDTO created = usuariosService.crearUsuario(dto);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String token = jwtUtil.generateToken(request.getUsername());

        UsuariosDTO usuario = usuariosService.obtenerPorUsername(request.getUsername());

        AuthResponse response = AuthResponse.builder()
                .token(token)
                .usuario(usuario)
                .build();

        return ResponseEntity.ok(response);
    }

}
