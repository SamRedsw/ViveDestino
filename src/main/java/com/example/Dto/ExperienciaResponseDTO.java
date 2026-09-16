package com.example.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienciaResponseDTO {

    private Long idExperiencia;
    private String nombre;
    private String descripcion;
    private String categoria;
    private String dificultad;
    private String duracion;
    private Double precioBase;
    private String estado;
    private String requisitos;
    private String politicaCancelacion;
    private List<String> imagenesUrl;
}