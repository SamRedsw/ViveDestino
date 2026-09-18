package com.example.Service.ServiceImpl;

import com.example.Dto.DtoCreate.ReservaCreateDTO;
import com.example.Dto.ReservaResponseDTO;
import com.example.Entity.Reserva;
import com.example.Entity.Salida;
import com.example.Entity.Usuario;
import com.example.Exception.ResourceNotFoundException;
import com.example.Repository.ReservaRepository;
import com.example.Repository.SalidaRepository;
import com.example.Repository.UsuarioRepository;
import com.example.Service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final SalidaRepository salidaRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public ReservaResponseDTO crearReserva(ReservaCreateDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + dto.getIdUsuario()));


        Salida salida = salidaRepository.findById(dto.getIdSalida())
                .orElseThrow(() -> new ResourceNotFoundException("Salida no encontrada con ID: " + dto.getIdSalida()));


        if (salida.getCuposDisponibles() < dto.getCantidadPersonas()) {
            throw new RuntimeException("No hay cupos suficientes para realizar la reserva. Cupos disponibles: " + salida.getCuposDisponibles());
        }


        salida.setCuposDisponibles(salida.getCuposDisponibles() - dto.getCantidadPersonas());
        salidaRepository.save(salida);

        Double totalCalculado = salida.getExperiencia().getPrecio()
                .multiply(BigDecimal.valueOf(dto.getCantidadPersonas()))
                .doubleValue();

        Reserva reserva = Reserva.builder()
                .usuario(usuario)
                .salida(salida)
                .cantidadPersonas(dto.getCantidadPersonas())
                .totalPagar(totalCalculado)
                .estadoReserva("PENDIENTE")
                .asistio(false)
                .build();

        Reserva guardada = reservaRepository.save(reserva);
        return mapToResponseDTO(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaResponseDTO> obtenerReservasPorUsuario(Long idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + idUsuario);
        }

        return reservaRepository.findByUsuario_IdUsuario(idUsuario)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ReservaResponseDTO cancelarReserva(Long idReserva) {
        Reserva reserva = reservaRepository.findById(idReserva)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con ID: " + idReserva));

        if ("CANCELADA".equalsIgnoreCase(reserva.getEstadoReserva())) {
            throw new RuntimeException("La reserva ya se encuentra cancelada");
        }

        Salida salida = reserva.getSalida();
        salida.setCuposDisponibles(salida.getCuposDisponibles() + reserva.getCantidadPersonas());
        salidaRepository.save(salida);

        reserva.setEstadoReserva("CANCELADA");
        Reserva actualizada = reservaRepository.save(reserva);

        return mapToResponseDTO(actualizada);
    }

    private ReservaResponseDTO mapToResponseDTO(Reserva r) {
        return ReservaResponseDTO.builder()
                .idReserva(r.getIdReserva())
                .idUsuario(r.getUsuario().getIdUsuario())
                .nombreViajero(r.getUsuario().getNombre())
                .idSalida(r.getSalida().getIdSalida())
                .nombreExperiencia(r.getSalida().getExperiencia().getNombre())
                .fechaSalida(r.getSalida().getFechaSalida())
                .cantidadPersonas(r.getCantidadPersonas())
                .totalPagar(BigDecimal.valueOf(r.getTotalPagar()))
                .estadoReserva(r.getEstadoReserva())
                .asistio(r.getAsistio())
                .fechaReserva(r.getFechaReserva())
                .build();
    }
}
