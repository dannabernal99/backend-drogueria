package com.drogueria.drogueria.Productos.servicios;

import com.drogueria.drogueria.Categorias.Categoria;
import com.drogueria.drogueria.Categorias.CategoriaRepositorio;
import com.drogueria.drogueria.Productos.Producto;
import com.drogueria.drogueria.Productos.ProductoDTO;
import com.drogueria.drogueria.Productos.ProductoMapper;
import com.drogueria.drogueria.Productos.ProductoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepositorio productoRepositorio;
    private final CategoriaRepositorio categoriaRepositorio;
    private final ProductoMapper productoMapper;

    public ProductoServicioImpl(ProductoRepositorio productoRepositorio,
                                CategoriaRepositorio categoriaRepositorio,
                                ProductoMapper productoMapper) {
        this.productoRepositorio = productoRepositorio;
        this.categoriaRepositorio = categoriaRepositorio;
        this.productoMapper = productoMapper;
    }

    @Override
    public List<ProductoDTO> findAll() {
        return productoRepositorio.findAll()
                .stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductoDTO> findById(Long id) {
        return productoRepositorio.findById(id)
                .map(productoMapper::toDTO);
    }

    @Override
    public ProductoDTO save(ProductoDTO dto) {
        Categoria categoria = null;
        if (dto.getCategoriaId() != null) {
            categoria = categoriaRepositorio.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        }
        Producto producto = productoMapper.toEntity(dto, categoria);
        Producto saved = productoRepositorio.save(producto);
        return productoMapper.toDTO(saved);
    }

    @Override
    public void deleteById(Long id) {
        productoRepositorio.deleteById(id);
    }

    @Override
    public ProductoDTO update(Long id, ProductoDTO dto) {
        return productoRepositorio.findById(id)
                .map(producto -> {
                    producto.setNombre(dto.getNombre());
                    producto.setPrecio(dto.getPrecio());
                    producto.setCantidad(dto.getCantidad());

                    if (dto.getCategoriaId() != null) {
                        Categoria categoria = categoriaRepositorio.findById(dto.getCategoriaId())
                                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
                        producto.setCategoria(categoria);
                    }

                    return productoMapper.toDTO(productoRepositorio.save(producto));
                }).orElse(null);
    }
}
