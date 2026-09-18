package com.example.Dto;

import com.example.Entity.Reserva;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalificacionResponseDTO {

    private Long idCalificacion;
    private String nombreViajero;
    private Integer puntaje;
    private String comentario;
    private Long idReserva;
    private LocalDateTime fecha;
}