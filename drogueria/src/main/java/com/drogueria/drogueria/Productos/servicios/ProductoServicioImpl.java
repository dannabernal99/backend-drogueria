package com.drogueria.drogueria.Productos.servicios;

import com.drogueria.drogueria.Productos.Producto;
import com.drogueria.drogueria.Productos.ProductoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepositorio productoRepositorio;

    public ProductoServicioImpl(ProductoRepositorio productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return productoRepositorio.findById(id);
    }

    @Override
    public List<Producto> findAll() {
        return productoRepositorio.findAll();
    }

    @Override
    public Producto save(Producto p) {
        return productoRepositorio.save(p);
    }

    @Override
    public void deleteById(Long id) {
        productoRepositorio.deleteById(id);
    }

    @Override
    public Producto update(Producto p) {
        return productoRepositorio.findById(p.getId())
                .map(producto -> {
                    producto.setNombre(p.getNombre());
                    producto.setPrecio(p.getPrecio());
                    producto.setCantidad(p.getCantidad());

                    return productoRepositorio.save(producto);
                })
                .orElse(null);
    }
}
