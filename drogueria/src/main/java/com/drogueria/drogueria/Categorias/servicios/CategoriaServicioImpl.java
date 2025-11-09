package com.drogueria.drogueria.Categorias.servicios;

import com.drogueria.drogueria.Categorias.Categoria;
import com.drogueria.drogueria.Categorias.CategoriaDTO;
import com.drogueria.drogueria.Categorias.CategoriaMapper;
import com.drogueria.drogueria.Categorias.CategoriaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServicioImpl implements CategoriaServicio{

    private final CategoriaRepositorio categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaServicioImpl(CategoriaRepositorio categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public CategoriaDTO crearCategoria(CategoriaDTO dto) {
        Categoria categoria = categoriaMapper.toEntity(dto);
        categoria = categoriaRepository.save(categoria);
        return categoriaMapper.toDTO(categoria);
    }

    @Override
    public CategoriaDTO actualizarCategoria(Long id, CategoriaDTO dto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        categoria = categoriaRepository.save(categoria);
        return categoriaMapper.toDTO(categoria);
    }

    @Override
    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public CategoriaDTO obtenerCategoriaPorId(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        return categoriaMapper.toDTO(categoria);
    }

    @Override
    public List<CategoriaDTO> obtenerTodasCategorias() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toDTO)
                .collect(Collectors.toList());
    }
}
