package com.example.Service.ServiceImpl;

import com.example.Dto.DtoCreate.SalidaCreateDTO;
import com.example.Dto.SalidaResponseDTO;
import com.example.Entity.Experiencia;
import com.example.Entity.Salida;
import com.example.Entity.Usuario;
import com.example.Exception.ResourceNotFoundException;
import com.example.Repository.ExperienciaRepository;
import com.example.Repository.SalidaRepository;
import com.example.Repository.UsuarioRepository;
import com.example.Service.SalidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalidaServiceImpl implements SalidaService {

    private final SalidaRepository salidaRepository;
    private final ExperienciaRepository experienciaRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public SalidaResponseDTO crearSalida(SalidaCreateDTO dto) {
        Experiencia experiencia = experienciaRepository.findById(dto.getIdExperiencia())
                .orElseThrow(() -> new ResourceNotFoundException("Experiencia no encontrada con ID: " + dto.getIdExperiencia()));

        Usuario guia = usuarioRepository.findById(dto.getIdGuia())
                .orElseThrow(() -> new ResourceNotFoundException("Guía no encontrado con ID: " + dto.getIdGuia()));

        Salida salida = Salida.builder()
                .experiencia(experiencia)
                .guia(guia)
                .fechaSalida(dto.getFechaSalida())
                .horaSalida(dto.getHoraSalida())
                .fechaRetorno(dto.getFechaRetorno())
                .cupoMaximo(dto.getCuposTotales())
                .cuposDisponibles(dto.getCuposTotales())
                .estadoOperativo(Salida.EstadoSalida.PROGRAMADA)
                .build();

        Salida guardada = salidaRepository.save(salida);
        return mapToResponseDTO(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SalidaResponseDTO> obtenerSalidasPorExperiencia(Long idExperiencia) {
        if (!experienciaRepository.existsById(idExperiencia)) {
            throw new ResourceNotFoundException("Experiencia no encontrada con ID: " + idExperiencia);
        }

        return salidaRepository.findByExperiencia_IdExperiencia(idExperiencia)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public SalidaResponseDTO obtenerPorId(Long idSalida) {
        Salida salida = salidaRepository.findById(idSalida)
                .orElseThrow(() -> new ResourceNotFoundException("Salida no encontrada con ID: " + idSalida));

        return mapToResponseDTO(salida);
    }

    private SalidaResponseDTO mapToResponseDTO(Salida s) {
        return SalidaResponseDTO.builder()
                .idSalida(s.getIdSalida())
                .idExperiencia(s.getExperiencia().getIdExperiencia())
                .nombreExperiencia(s.getExperiencia().getNombre())
                .idGuia(s.getGuia().getIdUsuario())
                .nombreGuia(s.getGuia().getNombre())
                .fechaSalida(s.getFechaSalida())
                .horaSalida(s.getHoraSalida())
                .fechaRetorno(s.getFechaRetorno())
                .cupoMaximo(s.getCupoMaximo())
                .cuposDisponibles(s.getCuposDisponibles())
                .estadoOperativo(s.getEstadoOperativo().name())
                .build();
    }
}