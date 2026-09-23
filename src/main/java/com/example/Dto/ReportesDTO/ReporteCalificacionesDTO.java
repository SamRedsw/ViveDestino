package com.example.Dto.ReportesDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReporteCalificacionesDTO {
    private Double promedioGeneralPlataforma;
    private long totalCalificaciones;
    private List<ExperienciaPromedioDTO> promedioPorExperiencia;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ExperienciaPromedioDTO {
        private Long experienciaId;
        private String tituloExperiencia;
        private Double promedioPuntuacion;
        private long cantidadResenas;
    }
}
