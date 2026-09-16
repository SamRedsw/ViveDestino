package com.example.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalidaResponseDTO {

    private Long idSalida;
    private Long idExperiencia;
    private String nombreExperiencia;
    private String nombreGuia;
    private LocalDateTime fechaHora;
    private Integer cupoTotal;
    private Integer cuposDisponibles;
    private String estadoOperativo;
}