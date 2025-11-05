package com.drogueria.drogueria.Productos.servicios;

import com.drogueria.drogueria.Productos.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoServicio {

    List<Producto> findAll();
    Optional<Producto> findById(Long id);
    Producto save(Producto p);
    void deleteById(Long id);
    Producto update(Producto p);

}