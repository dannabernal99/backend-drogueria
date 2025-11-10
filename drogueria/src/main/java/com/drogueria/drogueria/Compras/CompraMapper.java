package com.drogueria.drogueria.Compras;

import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class CompraMapper {

    public CompraDTO toDTO(Compra compra) {
        if (compra == null) return null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        CompraDTO dto = new CompraDTO();
        dto.setId(compra.getId());
        dto.setUsuarioId(compra.getUsuario().getId());
        dto.setUsuarioNombre(compra.getUsuario().getNombreCompleto());
        dto.setProductoId(compra.getProducto().getId());
        dto.setProductoNombre(compra.getProducto().getNombre());
        dto.setCantidad(compra.getCantidad());
        dto.setPrecioUnitario(compra.getPrecioUnitario());
        dto.setPrecioTotal(compra.getPrecioTotal());
        dto.setFechaCompra(compra.getFechaCompra().format(formatter));

        return dto;
    }
}