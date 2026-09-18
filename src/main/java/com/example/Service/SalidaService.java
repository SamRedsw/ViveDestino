package com.example.Service;

import com.example.Dto.DtoCreate.SalidaCreateDTO;
import com.example.Dto.SalidaResponseDTO;

import java.util.List;

public interface SalidaService {
    SalidaResponseDTO crearSalida(SalidaCreateDTO dto);
    List<SalidaResponseDTO> obtenerSalidasPorExperiencia(Long idExperiencia);
    SalidaResponseDTO obtenerPorId(Long idSalida);
}
