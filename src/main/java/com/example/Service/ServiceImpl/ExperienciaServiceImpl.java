package com.example.Service.ServiceImpl;

import com.example.Dto.DtoCreate.ExperienciaCreateDTO;
import com.example.Dto.ExperienciaResponseDTO;
import com.example.Entity.Experiencia;
import com.example.Exception.ResourceNotFoundException;
import com.example.Repository.ExperienciaRepository;
import com.example.Service.ExperienciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExperienciaServiceImpl implements ExperienciaService {

    private final ExperienciaRepository experienciaRepository;


    @Override
    @Transactional
    public ExperienciaResponseDTO crearExperiencia(ExperienciaCreateDTO dto) {
        Experiencia experiencia = Experiencia.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .ubicacion(dto.getCategoria())
                .precio(dto.getPrecio())
                .dificultad(Experiencia.Dificultad.valueOf(dto.getDificultad().toUpperCase()))
                .estado(Experiencia.EstadoExperiencia.PUBLICADA)
                .build();

        Experiencia guardada = experienciaRepository.save(experiencia);
        return mapToResponseDTO(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExperienciaResponseDTO> obtenerTodasPublicadas() {
        return experienciaRepository.findByEstado("PUBLICADA")
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ExperienciaResponseDTO obtenerPorId(Long id) {
        Experiencia experiencia = experienciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Experiencia no encontrada con ID: " + id));

        return mapToResponseDTO(experiencia);
    }

    private ExperienciaResponseDTO mapToResponseDTO(Experiencia e) {
        return ExperienciaResponseDTO.builder()
                .idExperiencia(e.getIdExperiencia())
                .nombre(e.getNombre())
                .descripcion(e.getDescripcion())
                .precio(e.getPrecio())
                .dificultad(e.getDificultad().name())
                .estado(e.getEstado().name())
                .build();
    }
}
