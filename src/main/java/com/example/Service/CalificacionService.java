package com.example.Service;

import com.example.Dto.CalificacionResponseDTO;
import com.example.Dto.DtoCreate.CalificacionCreateDTO;

import java.util.List;

public interface CalificacionService {
    CalificacionResponseDTO registrarCalificacion(CalificacionCreateDTO dto);
    List<CalificacionResponseDTO> obtenerPorExperiencia(Long idExperiencia);
}
