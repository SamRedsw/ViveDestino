package com.example.Dto.ReportesDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExperienciaDemandadaDTO {
    private Long experienciaId;
    private String tituloExperiencia;
    private String ubicacion;
    private long totalReservas;
    private long totalCuposReservados;
}