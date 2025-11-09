package com.drogueria.drogueria.Dashboard;

import com.drogueria.drogueria.Usuarios.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DashboardRepositorio extends JpaRepository<Usuarios, Long> {

    @Query("SELECT COUNT(u) FROM Usuarios u")
    long countUsuarios();

    @Query("SELECT COUNT(p) FROM Producto p")
    long countProductos();

    @Query("SELECT COUNT(c) FROM Categoria c")
    long countCategorias();

    // Crecimiento mensual de usuarios
    @Query("SELECT COUNT(u), MONTH(u.fechaCreacion) FROM Usuarios u GROUP BY MONTH(u.fechaCreacion) ORDER BY MONTH(u.fechaCreacion)")
    List<Object[]> countUsuariosPorMes();

    // Crecimiento mensual de productos
    @Query("SELECT COUNT(p), MONTH(p.fechaCreacion) FROM Producto p GROUP BY MONTH(p.fechaCreacion) ORDER BY MONTH(p.fechaCreacion)")
    List<Object[]> countProductosPorMes();

    // Crecimiento mensual de categorías
    @Query("SELECT COUNT(c), MONTH(c.fechaCreacion) FROM Categoria c GROUP BY MONTH(c.fechaCreacion) ORDER BY MONTH(c.fechaCreacion)")
    List<Object[]> countCategoriasPorMes();
}
