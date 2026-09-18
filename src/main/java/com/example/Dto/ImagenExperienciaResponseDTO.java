package com.example.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImagenExperienciaResponseDTO {
    private Long idImagen;
    private Long idExperiencia;
    private String urlImagen;
}
