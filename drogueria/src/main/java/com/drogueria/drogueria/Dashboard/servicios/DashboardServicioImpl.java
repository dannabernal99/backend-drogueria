package com.drogueria.drogueria.Dashboard.servicios;

import com.drogueria.drogueria.Dashboard.DashboardDTO;
import com.drogueria.drogueria.Dashboard.DashboardRepositorio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardServicioImpl implements DashboardServicio {

    private final DashboardRepositorio dashboardRepository;

    public DashboardServicioImpl(DashboardRepositorio dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    @Override
    public DashboardDTO obtenerEstadisticasGenerales() {
        long usuarios = dashboardRepository.countUsuarios();
        long productos = dashboardRepository.countProductos();
        long categorias = dashboardRepository.countCategorias();

        List<Long> crecimientoUsuarios = inicializarLista(12);
        List<Long> crecimientoProductos = inicializarLista(12);
        List<Long> crecimientoCategorias = inicializarLista(12);

        for (Object[] row : dashboardRepository.countUsuariosPorMes()) {
            Long cantidad = (Long) row[0];
            int mes = ((Number) row[1]).intValue();
            crecimientoUsuarios.set(mes - 1, cantidad);
        }

        for (Object[] row : dashboardRepository.countProductosPorMes()) {
            Long cantidad = (Long) row[0];
            int mes = ((Number) row[1]).intValue();
            crecimientoProductos.set(mes - 1, cantidad);
        }

        for (Object[] row : dashboardRepository.countCategoriasPorMes()) {
            Long cantidad = (Long) row[0];
            int mes = ((Number) row[1]).intValue();
            crecimientoCategorias.set(mes - 1, cantidad);
        }

        return new DashboardDTO(
                usuarios,
                productos,
                categorias,
                crecimientoUsuarios,
                crecimientoProductos,
                crecimientoCategorias
        );
    }

    private List<Long> inicializarLista(int size) {
        List<Long> lista = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            lista.add(0L);
        }
        return lista;
    }
}
