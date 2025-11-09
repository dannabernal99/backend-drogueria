package com.drogueria.drogueria.Categorias.servicios;

import com.drogueria.drogueria.Categorias.CategoriaDTO;

import java.util.List;

public interface CategoriaServicio {
    CategoriaDTO crearCategoria(CategoriaDTO dto);

    CategoriaDTO actualizarCategoria(Long id, CategoriaDTO dto);

    void eliminarCategoria(Long id);

    CategoriaDTO obtenerCategoriaPorId(Long id);

    List<CategoriaDTO> obtenerTodasCategorias();
}
