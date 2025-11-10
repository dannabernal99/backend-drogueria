package com.drogueria.drogueria.Compras;

import org.springframework.stereotype.Component;

@Component
public class CompraMapper {

    public CompraDTO toDTO(Compra compra) {
        if (compra == null) return null;

        CompraDTO dto = new CompraDTO();
        dto.setId(compra.getId());
        dto.setUsuarioId(compra.getUsuario().getId());
        dto.setUsuarioNombre(compra.getUsuario().getNombreCompleto());
        dto.setProductoId(compra.getProducto().getId());
        dto.setProductoNombre(compra.getProducto().getNombre());
        dto.setCantidad(compra.getCantidad());
        dto.setPrecioUnitario(compra.getPrecioUnitario());
        dto.setPrecioTotal(compra.getPrecioTotal());
        dto.setFechaCompra(compra.getFechaCompra());

        return dto;
    }
}