package com.drogueria.drogueria.Dashboard;

import com.drogueria.drogueria.Dashboard.servicios.DashboardServicio;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final DashboardServicio dashboardService;

    public DashboardController(DashboardServicio dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/admin")
    public DashboardDTO getDashboardAdmin() {
        return dashboardService.obtenerEstadisticasGenerales();
    }

    @GetMapping("/user")
    public DashboardDTO getDashboardUser() {
        return dashboardService.obtenerEstadisticasGenerales();
    }
}