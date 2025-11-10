package com.drogueria.drogueria.Compras;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompraDTO {
    private Long id;
    private Long usuarioId;
    private String usuarioNombre;
    private Long productoId;
    private String productoNombre;
    private Integer cantidad;
    private Double precioUnitario;
    private Double precioTotal;
    private LocalDateTime fechaCompra;
}
