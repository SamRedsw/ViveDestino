package com.example.Controller;

import com.example.Dto.DtoCreate.ExperienciaCreateDTO;
import com.example.Dto.ExperienciaResponseDTO;
import com.example.Service.ExperienciaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiencias")
@RequiredArgsConstructor
public class ExperienciaController {

    private final ExperienciaService experienciaService;

    @PostMapping
    public ResponseEntity<ExperienciaResponseDTO> crearExperiencia(@Valid @RequestBody ExperienciaCreateDTO dto) {
        ExperienciaResponseDTO creada = experienciaService.crearExperiencia(dto);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ExperienciaResponseDTO>> obtenerTodasPublicadas() {
        return ResponseEntity.ok(experienciaService.obtenerTodasPublicadas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExperienciaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(experienciaService.obtenerPorId(id));
    }
}