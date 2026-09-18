package com.example.Service;

import com.example.Dto.DtoCreate.ExperienciaCreateDTO;
import com.example.Dto.ExperienciaResponseDTO;

import java.util.List;

public interface ExperienciaService{

    ExperienciaResponseDTO crearExperiencia(ExperienciaCreateDTO dto);
    List<ExperienciaResponseDTO> obtenerTodasPublicadas();
    ExperienciaResponseDTO obtenerPorId(Long id);

}
