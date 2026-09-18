package com.example.Dto.DtoCreate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImagenExperienciaCreateDTO {
    @NotNull(message = "El ID de la experiencia es obligatorio")
    private Long idExperiencia;

    @NotBlank(message = "La URL de la imagen es obligatoria")
    private String urlImagen;
}
