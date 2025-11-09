package com.drogueria.drogueria.Productos;

import com.drogueria.drogueria.Categorias.Categoria;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "productos")
@Getter
@Setter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private Double precio;

    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}