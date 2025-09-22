package com.drogueria.drogueria.Productos;

import com.drogueria.drogueria.Productos.servicios.ProductoServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    private final ProductoServicio productoServicio;

    public ProductoController(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    @PostMapping
    public Producto save(@RequestBody Producto p){
        return productoServicio.save(p);
    }

    @GetMapping("/{id}")
    public Optional<Producto> getById(@PathVariable Long id){
        return productoServicio.findById(id);
    }
}