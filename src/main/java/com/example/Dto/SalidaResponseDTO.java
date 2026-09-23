package com.example.Dto;

import com.example.Entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalidaResponseDTO {

    private Long idSalida;
    private Long idGuia;
    private Long idExperiencia;
    private String nombreExperiencia;
    private String nombreGuia;
    private LocalDateTime fechaSalida;
    private LocalTime horaSalida;
    private LocalDate fechaRetorno;
    private Integer cupoMaximo;
    private Integer cuposDisponibles;
    private String estadoOperativo;
}