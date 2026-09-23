package com.example.Controller;

import com.example.Dto.ReportesDTO.ExperienciaDemandadaDTO;
import com.example.Dto.ReportesDTO.ReporteCalificacionesDTO;
import com.example.Dto.ReportesDTO.ReporteVentasDTO;
import com.example.Service.ReporteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/admin/reportes")
@PreAuthorize("hasRole('ADMINISTRADOR')")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    // 1. Reporte de Ventas (Permite filtrar opcionalmente por rango de fechas)
    @GetMapping("/ventas")
    public ResponseEntity<ReporteVentasDTO> getReporteVentas(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        return ResponseEntity.ok(reporteService.generarReporteVentas(fechaInicio, fechaFin));
    }

    // 2. Reporte de Experiencias Más Demandadas
    @GetMapping("/experiencias-demandadas")
    public ResponseEntity<List<ExperienciaDemandadaDTO>> getReporteExperienciasDemandadas() {
        return ResponseEntity.ok(reporteService.generarReporteExperienciasDemandadas());
    }

    // 3. Reporte de Calificaciones
    @GetMapping("/calificaciones")
    public ResponseEntity<ReporteCalificacionesDTO> getReporteCalificaciones() {
        return ResponseEntity.ok(reporteService.generarReporteCalificaciones());
    }
}
