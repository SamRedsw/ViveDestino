package com.example.Service;

import com.example.Dto.DtoCreate.PagoCreateDTO;
import com.example.Dto.PagoResponseDTO;

public interface PagoService {
    PagoResponseDTO procesarPago(PagoCreateDTO dto);
    PagoResponseDTO obtenerPorReserva(Long idReserva);
}
