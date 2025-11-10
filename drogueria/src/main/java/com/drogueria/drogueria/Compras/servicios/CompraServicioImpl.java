package com.drogueria.drogueria.Compras.servicios;

import com.drogueria.drogueria.Compras.*;
import com.drogueria.drogueria.Exception.UsuarioNoEncontradoException;
import com.drogueria.drogueria.Productos.Producto;
import com.drogueria.drogueria.Productos.ProductoRepositorio;
import com.drogueria.drogueria.Usuarios.UsuarioRepositorio;
import com.drogueria.drogueria.Usuarios.Usuarios;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompraServicioImpl implements CompraServicio {

    private final CompraRepositorio compraRepositorio;
    private final ProductoRepositorio productoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final CompraMapper compraMapper;

    public CompraServicioImpl(CompraRepositorio compraRepositorio,
                              ProductoRepositorio productoRepositorio,
                              UsuarioRepositorio usuarioRepositorio,
                              CompraMapper compraMapper) {
        this.compraRepositorio = compraRepositorio;
        this.productoRepositorio = productoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.compraMapper = compraMapper;
    }

    @Override
    @Transactional
    public CompraDTO realizarCompraPorUsername(String username, CompraRequestDTO request) {
        Usuarios usuario = usuarioRepositorio.findByUsername(username)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado con username: " + username));

        return procesarCompra(usuario, request);
    }

    private CompraDTO procesarCompra(Usuarios usuario, CompraRequestDTO request) {
        Producto producto = productoRepositorio.findById(request.getProductoId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (producto.getCantidad() < request.getCantidad()) {
            throw new RuntimeException("Stock insuficiente. Disponible: " + producto.getCantidad());
        }

        if (request.getCantidad() <= 0) {
            throw new RuntimeException("La cantidad debe ser mayor a 0");
        }

        Compra compra = new Compra();
        compra.setUsuario(usuario);
        compra.setProducto(producto);
        compra.setCantidad(request.getCantidad());
        compra.setPrecioUnitario(producto.getPrecio());
        compra.setPrecioTotal(producto.getPrecio() * request.getCantidad());

        producto.setCantidad(producto.getCantidad() - request.getCantidad());
        productoRepositorio.save(producto);

        Compra savedCompra = compraRepositorio.save(compra);

        return compraMapper.toDTO(savedCompra);
    }

    @Override
    public List<CompraDTO> obtenerComprasPorUsuario(Long usuarioId) {
        return compraRepositorio.findByUsuarioId(usuarioId)
                .stream()
                .map(compraMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CompraDTO> obtenerTodasLasCompras() {
        return compraRepositorio.findAll()
                .stream()
                .map(compraMapper::toDTO)
                .collect(Collectors.toList());
    }
}