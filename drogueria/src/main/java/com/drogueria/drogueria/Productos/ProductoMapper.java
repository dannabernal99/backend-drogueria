package com.drogueria.drogueria.Productos;

import com.drogueria.drogueria.Categorias.Categoria;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoDTO toDTO(Producto producto) {
        if (producto == null) return null;

        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setCantidad(producto.getCantidad());

        if (producto.getCategoria() != null) {
            dto.setCategoriaId(producto.getCategoria().getId());
            dto.setCategoriaNombre(producto.getCategoria().getNombre());
        }

        return dto;
    }

    public ProductoCategoriaDTO toProductoCategoriaDTO(Producto producto) {
        if (producto == null) return null;

        ProductoCategoriaDTO dto = new ProductoCategoriaDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setCantidad(producto.getCantidad());

        if (producto.getCategoria() != null) {
            dto.setCategoriaId(producto.getCategoria().getId());
            dto.setCategoriaNombre(producto.getCategoria().getNombre());
            dto.setCategoriaDescripcion(producto.getCategoria().getDescripcion());
        }

        return dto;
    }

    public Producto toEntity(ProductoDTO dto, Categoria categoria) {
        if (dto == null) return null;

        Producto producto = new Producto();
        producto.setId(dto.getId());
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setCantidad(dto.getCantidad());
        producto.setCategoria(categoria);

        return producto;
    }
}