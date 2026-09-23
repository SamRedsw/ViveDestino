package com.example.Controller;

import com.example.Dto.DtoCreate.ReservaCreateDTO;
import com.example.Dto.ReservaResponseDTO;
import com.example.Service.ReservaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaResponseDTO> crearReserva(@Valid @RequestBody ReservaCreateDTO dto) {
        ReservaResponseDTO creada = reservaService.crearReserva(dto);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ReservaResponseDTO>> obtenerPorUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(reservaService.obtenerReservasPorUsuario(idUsuario));
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<ReservaResponseDTO> cancelarReserva(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.cancelarReserva(id));
    }

    @PutMapping("/{id}/marcar-asistencia")
    @PreAuthorize("hasAnyAuthority('GUIA', 'ROLE_GUIA', 'ADMINISTRADOR', 'ROLE_ADMINISTRADOR')")
    public ResponseEntity<ReservaResponseDTO> marcarAsistencia(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.marcarAsistencia(id));
    }
}