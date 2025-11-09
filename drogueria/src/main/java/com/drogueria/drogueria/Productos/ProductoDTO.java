package com.drogueria.drogueria.Productos;

import lombok.Data;

@Data
public class ProductoDTO {

    private Long id;
    private String nombre;
    private Double precio;
    private Integer cantidad;
    private Long categoriaId;
    private String categoriaNombre;
}