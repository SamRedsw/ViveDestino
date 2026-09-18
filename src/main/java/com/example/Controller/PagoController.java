package com.example.Controller;

import com.example.Dto.DtoCreate.PagoCreateDTO;
import com.example.Dto.PagoResponseDTO;
import com.example.Service.PagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @PostMapping
    public ResponseEntity<PagoResponseDTO> procesarPago(@Valid @RequestBody PagoCreateDTO dto) {
        PagoResponseDTO pago = pagoService.procesarPago(dto);
        return new ResponseEntity<>(pago, HttpStatus.CREATED);
    }

    @GetMapping("/reserva/{idReserva}")
    public ResponseEntity<PagoResponseDTO> obtenerPorReserva(@PathVariable Long idReserva) {
        return ResponseEntity.ok(pagoService.obtenerPorReserva(idReserva));
    }
}