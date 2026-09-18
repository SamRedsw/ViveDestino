package com.example.Service.ServiceImpl;

import com.example.Dto.CalificacionResponseDTO;
import com.example.Dto.DtoCreate.CalificacionCreateDTO;
import com.example.Entity.Calificacion;
import com.example.Entity.Reserva;
import com.example.Exception.ResourceNotFoundException;
import com.example.Repository.CalificacionRepository;
import com.example.Repository.ExperienciaRepository;
import com.example.Repository.ReservaRepository;
import com.example.Service.CalificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CalificacionServiceImpl implements CalificacionService {

    private final CalificacionRepository calificacionRepository;
    private final ReservaRepository reservaRepository;
    private final ExperienciaRepository experienciaRepository;

    @Override
    @Transactional
    public CalificacionResponseDTO registrarCalificacion(CalificacionCreateDTO dto) {
        Reserva reserva = reservaRepository.findById(dto.getIdReserva())
                .orElseThrow(() -> new ResourceNotFoundException("Reserva no encontrada con ID: " + dto.getIdReserva()));

        if (Boolean.FALSE.equals(reserva.getAsistio())) {
            throw new RuntimeException("Solo es posible calificar experiencias a las que se haya asistido");
        }

        Calificacion calificacion = Calificacion.builder()
                .reserva(reserva)
                .viajero(reserva.getUsuario())
                .experiencia(reserva.getSalida().getExperiencia())
                .puntaje(dto.getPuntaje())
                .comentario(dto.getComentario())
                .build();

        Calificacion guardada = calificacionRepository.save(calificacion);
        return mapToResponseDTO(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CalificacionResponseDTO> obtenerPorExperiencia(Long idExperiencia) {
        if (!experienciaRepository.existsById(idExperiencia)) {
            throw new ResourceNotFoundException("Experiencia no encontrada con ID: " + idExperiencia);
        }
        return calificacionRepository.findByExperiencia_IdExperiencia(idExperiencia)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private CalificacionResponseDTO mapToResponseDTO(Calificacion c) {
        return CalificacionResponseDTO.builder()
                .idCalificacion(c.getIdCalificacion())
                .idReserva(c.getReserva().getIdReserva())
                .nombreViajero(c.getViajero().getNombre())
                .puntaje(c.getPuntaje())
                .comentario(c.getComentario())
                .fecha(c.getFechaCreacion())
                .build();
    }
}
