package com.drogueria.drogueria.Productos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductoCategoriaDTO {
    private Long id;
    private String nombre;
    private Double precio;
    private Integer cantidad;

    private Long categoriaId;
    private String categoriaNombre;
    private String categoriaDescripcion;
}
