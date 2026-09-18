package com.example.Service.ServiceImpl;

import com.example.Dto.DtoCreate.PagoCreateDTO;
import com.example.Dto.PagoResponseDTO;
import com.example.Entity.Pago;
import com.example.Entity.Reserva;
import com.example.Exception.ResourceNotFoundException;
import com.example.Repository.PagoRepository;
import com.example.Repository.ReservaRepository;
import com.example.Service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;
    private final ReservaRepository reservaRepository;

    @Override
    @Transactional
    public PagoResponseDTO procesarPago(PagoCreateDTO dto) {
        Reserva reserva = reservaRepository.findById(dto.getIdReserva())
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con ID: " + dto.getIdReserva()));

        if (Reserva.EstadoReserva.CANCELADA.equals(reserva.getEstadoReserva())) {
            throw new RuntimeException("No se puede procesar el pago de una reserva cancelada");
        }

        if (reserva.getPago() != null) {
            throw new RuntimeException("La reserva ya cuenta con un pago registrado");
        }

        Pago pago = Pago.builder()
                .reserva(reserva)
                .monto(dto.getMonto())
                .metodoPago(dto.getMetodoPago())
                .estadoPago(Pago.EstadoPago.COMPLETADO)
                .build();

        Pago guardado = pagoRepository.save(pago);

        reserva.setEstadoReserva(Reserva.EstadoReserva.CONFIRMADA);
        reservaRepository.save(reserva);

        return mapToResponseDTO(guardado);
    }

    @Override
    @Transactional(readOnly = true)
    public PagoResponseDTO obtenerPorReserva(Long idReserva) {
        if (!reservaRepository.existsById(idReserva)) {
            throw new ResourceNotFoundException("Reserva no encontrada con ID: " + idReserva);
        }

        Pago pago = pagoRepository.findByReserva_IdReserva(idReserva)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró ningún pago asociado a la reserva ID: " + idReserva));

        return mapToResponseDTO(pago);
    }

    private PagoResponseDTO mapToResponseDTO(Pago p) {
        return PagoResponseDTO.builder()
                .idPago(p.getIdPago())
                .idReserva(p.getReserva().getIdReserva())
                .monto(p.getMonto())
                .metodoPago(p.getMetodoPago())
                .estadoPago(p.getEstadoPago())
                .fechaPago(p.getFechaPago())
                .build();
    }
}
