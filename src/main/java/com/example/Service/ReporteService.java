package com.example.Service;

import com.example.Dto.ReportesDTO.ExperienciaDemandadaDTO;
import com.example.Dto.ReportesDTO.ReporteCalificacionesDTO;
import com.example.Dto.ReportesDTO.ReporteVentasDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReporteService {
    ReporteVentasDTO generarReporteVentas(LocalDate fechaInicio, LocalDate fechaFin);
    List<ExperienciaDemandadaDTO> generarReporteExperienciasDemandadas();
    ReporteCalificacionesDTO generarReporteCalificaciones();
}
