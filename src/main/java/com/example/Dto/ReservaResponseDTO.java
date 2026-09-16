package com.example.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservaResponseDTO {

    private Long idReserva;
    private String nombreViajero;
    private String nombreExperiencia;
    private LocalDateTime fechaSalida;
    private Integer cantidadPersonas;
    private Double totalPagar;
    private String estadoReserva;
    private LocalDateTime fechaReserva;
}