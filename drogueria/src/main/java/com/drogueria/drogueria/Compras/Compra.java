package com.drogueria.drogueria.Compras;

import com.drogueria.drogueria.Productos.Producto;
import com.drogueria.drogueria.Usuarios.Usuarios;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "compras")
@Data
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuarios usuario;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Double precioUnitario;

    @Column(nullable = false)
    private Double precioTotal;

    @Column(nullable = false)
    private LocalDateTime fechaCompra;

    @PrePersist
    protected void onCreate() {
        fechaCompra = LocalDateTime.now();
    }
}
