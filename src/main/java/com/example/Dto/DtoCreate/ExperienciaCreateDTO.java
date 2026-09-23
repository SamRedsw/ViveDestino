package com.example.Dto.DtoCreate;

import com.example.Entity.Experiencia;
import com.example.Entity.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @NotBlank(message = "La dificultad es obligatoria")
    private String dificultad;

    @NotBlank(message = "El estadfo es obligatoria")
    private String estado;

    private String duracion;

    @NotNull(message = "El precio base es obligatorio")
    @Min(value = 0, message = "El precio debe ser positivo")
    private BigDecimal precio;

    private String requisitos;

    private String politicaCancelacion;

    // Cambiar 'private Usuario organizador;' por el ID:
    @NotNull(message = "El ID del organizador es obligatorio")
    private Long idOrganizador;

    @NotBlank(message = "La ubicación es obligatoria")
    private String ubicacion;

    @Schema(description = "Lista de URLs de imágenes asociadas a la experiencia")
    private List<String> imagenesUrl;
}