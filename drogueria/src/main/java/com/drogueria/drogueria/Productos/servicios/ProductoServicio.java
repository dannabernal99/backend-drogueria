package com.drogueria.drogueria.Productos.servicios;

import com.drogueria.drogueria.Productos.Producto;
import com.drogueria.drogueria.Productos.ProductoCategoriaDTO;
import com.drogueria.drogueria.Productos.ProductoDTO;

import java.util.List;
import java.util.Optional;

public interface ProductoServicio {

    List<ProductoDTO> findAll();
    Optional<ProductoDTO> findById(Long id);
    ProductoDTO save(ProductoDTO p);
    void deleteById(Long id);
    ProductoDTO update(Long id, ProductoDTO p);
    List<ProductoCategoriaDTO> findAllWithCategoria();
}