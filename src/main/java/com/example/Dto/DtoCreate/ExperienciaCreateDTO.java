package com.example.Dto.DtoCreate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExperienciaCreateDTO {

    @NotBlank(message = "El nombre de la experiencia es obligatorio")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotBlank(message = "La categoría es obligatoria")
    private String categoria;

    @NotBlank(message = "La dificultad es obligatoria")
    private String dificultad;

    private String duracion;

    @NotNull(message = "El precio base es obligatorio")
    @Min(value = 0, message = "El precio debe ser positivo")
    private BigDecimal precio;

    private String requisitos;
    private String politicaCancelacion;
    private List<String> imagenesUrl;
}