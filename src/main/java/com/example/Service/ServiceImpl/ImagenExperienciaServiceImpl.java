package com.example.Service.ServiceImpl;

import com.example.Dto.DtoCreate.ImagenExperienciaCreateDTO;
import com.example.Dto.ImagenExperienciaResponseDTO;
import com.example.Entity.Experiencia;
import com.example.Entity.ImagenExperiencia;
import com.example.Exception.ResourceNotFoundException;
import com.example.Repository.ExperienciaRepository;
import com.example.Repository.ImagenExperienciaRepository;
import com.example.Service.ImagenExperienciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImagenExperienciaServiceImpl implements ImagenExperienciaService {

    private final ImagenExperienciaRepository imagenExperienciaRepository;
    private final ExperienciaRepository experienciaRepository;

    @Override
    @Transactional
    public ImagenExperienciaResponseDTO agregarImagen(ImagenExperienciaCreateDTO dto) {
        Experiencia experiencia = experienciaRepository.findById(dto.getIdExperiencia())
                .orElseThrow(() -> new ResourceNotFoundException("Experiencia no encontrada con ID: " + dto.getIdExperiencia()));

        ImagenExperiencia imagen = ImagenExperiencia.builder()
                .experiencia(experiencia)
                .urlImagen(dto.getUrlImagen())
                .build();

        ImagenExperiencia guardada = imagenExperienciaRepository.save(imagen);
        return mapToResponseDTO(guardada);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ImagenExperienciaResponseDTO> obtenerImagenesPorExperiencia(Long idExperiencia) {
        if (!experienciaRepository.existsById(idExperiencia)) {
            throw new ResourceNotFoundException("Experiencia no encontrada con ID: " + idExperiencia);
        }

        return imagenExperienciaRepository.findByExperiencia_IdExperiencia(idExperiencia)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void eliminarImagen(Long idImagen) {
        ImagenExperiencia imagen = imagenExperienciaRepository.findById(idImagen)
                .orElseThrow(() -> new ResourceNotFoundException("Imagen no encontrada con ID: " + idImagen));

        imagenExperienciaRepository.delete(imagen);
    }

    private ImagenExperienciaResponseDTO mapToResponseDTO(ImagenExperiencia img) {
        return ImagenExperienciaResponseDTO.builder()
                .idImagen(img.getIdImagen())
                .idExperiencia(img.getExperiencia().getIdExperiencia())
                .urlImagen(img.getUrlImagen())
                .build();
    }
}
