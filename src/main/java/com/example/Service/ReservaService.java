package com.example.Service;

import com.example.Dto.DtoCreate.ReservaCreateDTO;
import com.example.Dto.ReservaResponseDTO;

import java.util.List;

public interface ReservaService {
    ReservaResponseDTO crearReserva(ReservaCreateDTO dto);
    List<ReservaResponseDTO> obtenerReservasPorUsuario(Long idUsuario);
    ReservaResponseDTO cancelarReserva(Long idReserva);
    ReservaResponseDTO marcarAsistencia(Long idReserva);
}
