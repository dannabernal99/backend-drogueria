package com.drogueria.drogueria.Compras.servicios;

import com.drogueria.drogueria.Compras.CompraDTO;
import com.drogueria.drogueria.Compras.CompraRequestDTO;

import java.util.List;

public interface CompraServicio {
    CompraDTO realizarCompra(Long usuarioId, CompraRequestDTO request);
    List<CompraDTO> obtenerComprasPorUsuario(Long usuarioId);
    List<CompraDTO> obtenerTodasLasCompras();
}