package com.example.Controller;

import com.example.Dto.DtoCreate.SalidaCreateDTO;
import com.example.Dto.SalidaResponseDTO;
import com.example.Service.SalidaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salidas")
@RequiredArgsConstructor
public class SalidaController {

    private final SalidaService salidaService;

    @PostMapping
    public ResponseEntity<SalidaResponseDTO> crearSalida(@Valid @RequestBody SalidaCreateDTO dto) {
        SalidaResponseDTO creada = salidaService.crearSalida(dto);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @GetMapping("/experiencia/{idExperiencia}")
    public ResponseEntity<List<SalidaResponseDTO>> obtenerPorExperiencia(@PathVariable Long idExperiencia) {
        return ResponseEntity.ok(salidaService.obtenerSalidasPorExperiencia(idExperiencia));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalidaResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(salidaService.obtenerPorId(id));
    }
}