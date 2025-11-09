package com.drogueria.drogueria.Dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {
    private long totalUsuarios;
    private long totalProductos;
    private long totalCategorias;

    private List<Long> crecimientoUsuarios;
    private List<Long> crecimientoProductos;
    private List<Long> crecimientoCategorias;
}