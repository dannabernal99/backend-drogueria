package com.drogueria.drogueria.SecurityConfig;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final int BEARER_PREFIX_LENGTH = 7;

    private static final List<String> PUBLIC_PATHS = Arrays.asList(
            "/api/v1/auth/login",
            "/api/v1/auth/register",
            "/api/v1/auth/refresh",
            "/api/v1/public/",
            "/actuator/health",
            "/swagger-ui",
            "/v3/api-docs"
    );

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // Permitir peticiones OPTIONS (CORS preflight)
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            logger.debug("Petición OPTIONS detectada, permitiendo sin autenticación");
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader(AUTHORIZATION_HEADER);

        // Si no hay header Authorization o no empieza con "Bearer ", continuar sin autenticar
        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            logger.debug("No se encontró token JWT en la petición a: {}", request.getRequestURI());
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = authHeader.substring(BEARER_PREFIX_LENGTH);
            final String username = jwtUtil.extractUsername(jwt);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = loadUserDetails(username);

                if (userDetails != null) {
                    if (jwtUtil.validateToken(jwt, userDetails)) {
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(
                                        userDetails,
                                        null,
                                        userDetails.getAuthorities()
                                );

                        authToken.setDetails(
                                new WebAuthenticationDetailsSource().buildDetails(request)
                        );

                        SecurityContextHolder.getContext().setAuthentication(authToken);

                        logger.debug("Usuario '{}' autenticado exitosamente", username);
                    } else {
                        logger.warn("Token JWT inválido para usuario: {}", username);
                    }
                }
            }
        } catch (ExpiredJwtException e) {
            logger.error("Token JWT expirado: {}", e.getMessage());
            setErrorResponse(response, "Token expirado", HttpServletResponse.SC_UNAUTHORIZED);
            return;
        } catch (MalformedJwtException e) {
            logger.error("Token JWT malformado: {}", e.getMessage());
            setErrorResponse(response, "Token inválido", HttpServletResponse.SC_UNAUTHORIZED);
            return;
        } catch (SignatureException e) {
            logger.error("Firma JWT inválida: {}", e.getMessage());
            setErrorResponse(response, "Firma del token inválida", HttpServletResponse.SC_UNAUTHORIZED);
            return;
        } catch (IllegalArgumentException e) {
            logger.error("Token JWT vacío o nulo: {}", e.getMessage());
            setErrorResponse(response, "Token no proporcionado", HttpServletResponse.SC_BAD_REQUEST);
            return;
        } catch (Exception e) {
            logger.error("Error inesperado al procesar el token JWT: {}", e.getMessage(), e);
            setErrorResponse(response, "Error al procesar autenticación", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private UserDetails loadUserDetails(String username) {
        try {
            return userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            logger.error("Usuario no encontrado: {}", username);
            return null;
        } catch (Exception e) {
            logger.error("Error al cargar usuario '{}': {}", username, e.getMessage());
            return null;
        }
    }

    private void setErrorResponse(HttpServletResponse response, String message, int status) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
                String.format("{\"error\": \"%s\", \"status\": %d}", message, status)
        );
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();

        logger.debug("Verificando si la ruta '{}' debe ser filtrada", path);

        boolean shouldNotFilter = PUBLIC_PATHS.stream()
                .anyMatch(path::startsWith);

        if (shouldNotFilter) {
            logger.debug("Ruta '{}' es pública, saltando filtro JWT", path);
        }

        return shouldNotFilter;
    }
}