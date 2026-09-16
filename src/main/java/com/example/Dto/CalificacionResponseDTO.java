package com.example.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalificacionResponseDTO {

    private Long idCalificacion;
    private String nombreViajero;
    private Integer puntaje;
    private String comentario;
    private LocalDateTime fecha;
}