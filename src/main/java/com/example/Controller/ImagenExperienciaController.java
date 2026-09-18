package com.example.Controller;

import com.example.Dto.DtoCreate.ImagenExperienciaCreateDTO;
import com.example.Dto.ImagenExperienciaResponseDTO;
import com.example.Service.ImagenExperienciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/imagenes-experiencia")
@RequiredArgsConstructor
public class ImagenExperienciaController {

    private final ImagenExperienciaService imagenExperienciaService;

    @PostMapping
    public ResponseEntity<ImagenExperienciaResponseDTO> agregarImagen(@Valid @RequestBody ImagenExperienciaCreateDTO dto) {
        ImagenExperienciaResponseDTO creada = imagenExperienciaService.agregarImagen(dto);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @GetMapping("/experiencia/{idExperiencia}")
    public ResponseEntity<List<ImagenExperienciaResponseDTO>> obtenerPorExperiencia(@PathVariable Long idExperiencia) {
        return ResponseEntity.ok(imagenExperienciaService.obtenerImagenesPorExperiencia(idExperiencia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarImagen(@PathVariable Long id) {
        imagenExperienciaService.eliminarImagen(id);
        return ResponseEntity.noContent().build();
    }
}