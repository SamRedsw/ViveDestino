package com.example.Controller;

import com.example.Dto.DtoCreate.CalificacionCreateDTO;
import com.example.Dto.CalificacionResponseDTO;
import com.example.Service.CalificacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calificaciones")
@RequiredArgsConstructor
public class CalificacionController {

    private final CalificacionService calificacionService;

    @PostMapping
    public ResponseEntity<CalificacionResponseDTO> registrarCalificacion(@Valid @RequestBody CalificacionCreateDTO dto) {
        CalificacionResponseDTO creada = calificacionService.registrarCalificacion(dto);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @GetMapping("/experiencia/{idExperiencia}")
    public ResponseEntity<List<CalificacionResponseDTO>> obtenerPorExperiencia(@PathVariable Long idExperiencia) {
        return ResponseEntity.ok(calificacionService.obtenerPorExperiencia(idExperiencia));
    }
}