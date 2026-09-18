package com.example.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaResponseDTO {

    private Long idUsuario;
    private Long idReserva;
    private Long idSalida;
    private String nombreViajero;
    private String nombreExperiencia;
    private LocalDateTime fechaSalida;
    private Integer cantidadPersonas;
    private BigDecimal totalPagar;
    private Boolean asistio;
    private String estadoReserva;
    private LocalDateTime fechaReserva;
}