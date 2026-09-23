package com.example.Service.ServiceImpl;

import com.example.Dto.ReportesDTO.ExperienciaDemandadaDTO;
import com.example.Dto.ReportesDTO.ReporteCalificacionesDTO;
import com.example.Dto.ReportesDTO.ReporteVentasDTO;
import com.example.Repository.CalificacionRepository;
import com.example.Repository.PagoRepository;
import com.example.Repository.ReservaRepository;
import com.example.Service.ReporteService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteServiceImpl implements ReporteService {

    private final PagoRepository pagoRepository;
    private final ReservaRepository reservaRepository;
    private final CalificacionRepository calificacionRepository;

    public ReporteServiceImpl(PagoRepository pagoRepository,
                              ReservaRepository reservaRepository,
                              CalificacionRepository calificacionRepository) {
        this.pagoRepository = pagoRepository;
        this.reservaRepository = reservaRepository;
        this.calificacionRepository = calificacionRepository;
    }

    @Override
    public ReporteVentasDTO generarReporteVentas(LocalDate fechaInicio, LocalDate fechaFin) {
        LocalDateTime inicio = (fechaInicio != null) ? fechaInicio.atStartOfDay() : null;
        LocalDateTime fin = (fechaFin != null) ? fechaFin.atTime(LocalTime.MAX) : null;

        List<Object[]> resultados = pagoRepository.obtenerMetricasVentas(inicio, fin);

        long totalTransacciones = 0;
        BigDecimal ingresosTotales = BigDecimal.ZERO;

        if (!resultados.isEmpty() && resultados.get(0) != null) {
            Object[] fila = resultados.get(0);
            totalTransacciones = ((Number) fila[0]).longValue();
            ingresosTotales = (BigDecimal) fila[1];
        }

        BigDecimal promedio = totalTransacciones > 0
                ? ingresosTotales.divide(BigDecimal.valueOf(totalTransacciones), 2, RoundingMode.HALF_UP)
                : BigDecimal.ZERO;

        return ReporteVentasDTO.builder()
                .fechaInicio(fechaInicio)
                .fechaFin(fechaFin)
                .totalTransaccionesAprobadas(totalTransacciones)
                .ingresosTotales(ingresosTotales)
                .promedioPorVenta(promedio)
                .build();
    }


    @Override
    public List<ExperienciaDemandadaDTO> generarReporteExperienciasDemandadas() {
        List<Object[]> resultados = reservaRepository.obtenerExperienciasMasDemandadas();

        return resultados.stream()
                .map(fila -> ExperienciaDemandadaDTO.builder()
                        .experienciaId((Long) fila[0])
                        .tituloExperiencia((String) fila[1])
                        .ubicacion((String) fila[2])
                        .totalReservas(((Number) fila[3]).longValue())
                        .totalCuposReservados(((Number) fila[4]).longValue())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ReporteCalificacionesDTO generarReporteCalificaciones() {
        Double promedioGeneral = calificacionRepository.obtenerPromedioGeneral();


        long totalCalificaciones = calificacionRepository.count();


        List<Object[]> resultados = calificacionRepository.obtenerPromedioPorExperiencia();


        List<ReporteCalificacionesDTO.ExperienciaPromedioDTO> porExperiencia = resultados.stream()
                .map(fila -> ReporteCalificacionesDTO.ExperienciaPromedioDTO.builder()
                        .experienciaId((Long) fila[0])
                        .tituloExperiencia((String) fila[1]) // Contiene e.nombre
                        .promedioPuntuacion(fila[2] != null ? ((Number) fila[2]).doubleValue() : 0.0)
                        .cantidadResenas(fila[3] != null ? ((Number) fila[3]).longValue() : 0L)
                        .build())
                .collect(Collectors.toList());


        return ReporteCalificacionesDTO.builder()
                .promedioGeneralPlataforma(promedioGeneral != null ? promedioGeneral : 0.0)
                .totalCalificaciones(totalCalificaciones)
                .promedioPorExperiencia(porExperiencia)
                .build();
    }
}
