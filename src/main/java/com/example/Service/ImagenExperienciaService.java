package com.example.Service;

import com.example.Dto.DtoCreate.ImagenExperienciaCreateDTO;
import com.example.Dto.ImagenExperienciaResponseDTO;

import java.util.List;

public interface ImagenExperienciaService {
    ImagenExperienciaResponseDTO agregarImagen(ImagenExperienciaCreateDTO dto);
    List<ImagenExperienciaResponseDTO> obtenerImagenesPorExperiencia(Long idExperiencia);
    void eliminarImagen(Long idImagen);
}
