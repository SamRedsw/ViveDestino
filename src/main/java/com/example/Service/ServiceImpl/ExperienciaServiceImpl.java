package com.example.Service.ServiceImpl;

import com.example.Dto.DtoCreate.ExperienciaCreateDTO;
import com.example.Dto.ExperienciaResponseDTO;
import com.example.Entity.Experiencia;
import com.example.Entity.ImagenExperiencia;
import com.example.Entity.Usuario;
import com.example.Exception.ResourceNotFoundException;
import com.example.Repository.ExperienciaRepository;
import com.example.Repository.UsuarioRepository;
import com.example.Service.ExperienciaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExperienciaServiceImpl implements ExperienciaService {

    private final ExperienciaRepository experienciaRepository;
    private final UsuarioRepository usuarioRepository;


    @Override
    @Transactional
    public ExperienciaResponseDTO crearExperiencia(ExperienciaCreateDTO dto) {
        // 1. Buscar el organizador por su ID
        Usuario organizador = usuarioRepository.findById(dto.getIdOrganizador())
                .orElseThrow(() -> new EntityNotFoundException("Organizador no encontrado con el ID: " + dto.getIdOrganizador()));

        // 2. Construir la entidad con el organizador consultado
        Experiencia experiencia = Experiencia.builder()
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .ubicacion(dto.getUbicacion())
                .precio(dto.getPrecio())
                .dificultad(Experiencia.Dificultad.valueOf(dto.getDificultad().toUpperCase()))
                .estado(Experiencia.EstadoExperiencia.ACTIVA)
                .duracion(dto.getDuracion())
                .organizador(organizador)
                .requisitos(dto.getRequisitos())
                .politicaCancelacion(dto.getPoliticaCancelacion())
                .build();

        // 3. Guardar y mapear la respuesta
        Experiencia guardada = experienciaRepository.save(experiencia);
        return mapToResponseDTO(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExperienciaResponseDTO> obtenerTodasPublicadas() {
        List<Experiencia> experiencias = experienciaRepository.findByEstado(Experiencia.EstadoExperiencia.ACTIVA);

        return experiencias.stream()
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
                .duracion(e.getDuracion())
                .requisitos(e.getRequisitos())
                .politicaCancelacion(e.getPoliticaCancelacion())
                .dificultad(e.getDificultad().name())
                .estado(e.getEstado().name())
                .build();
    }
}
