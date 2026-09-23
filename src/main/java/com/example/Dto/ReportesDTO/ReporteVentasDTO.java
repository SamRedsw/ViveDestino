package com.example.Dto.ReportesDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReporteVentasDTO {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private long totalTransaccionesAprobadas;
    private BigDecimal ingresosTotales;
    private BigDecimal promedioPorVenta;
}