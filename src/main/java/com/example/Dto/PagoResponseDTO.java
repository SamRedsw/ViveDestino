package com.example.Dto;

import com.example.Entity.Pago;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagoResponseDTO {

    private Long idPago;
    private Long idReserva;
    private BigDecimal monto;
    private String metodoPago;
    private String referencia;
    private Pago.EstadoPago estadoPago;
    private LocalDateTime fechaPago;
}