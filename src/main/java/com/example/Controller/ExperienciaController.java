package com.example.Controller;

import com.example.Dto.DtoCreate.ExperienciaCreateDTO;
import com.example.Dto.ExperienciaResponseDTO;
import com.example.Service.ExperienciaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/experiencias")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ExperienciaController {

    private final ExperienciaService experienciaService;

    @PostMapping
    public ResponseEntity<ExperienciaResponseDTO> crearExperiencia(@Valid @RequestBody ExperienciaCreateDTO dto) {
        ExperienciaResponseDTO creada = experienciaService.crearExperiencia(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
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