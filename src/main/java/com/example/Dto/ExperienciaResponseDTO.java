package com.example.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExperienciaResponseDTO {

    private Long idExperiencia;
    private String nombre;
    private String descripcion;
    private String dificultad;
    private String duracion;
    private BigDecimal precio;
    private String estado;
    private String requisitos;
    private String politicaCancelacion;
    private List<String> imagenesUrl;
}