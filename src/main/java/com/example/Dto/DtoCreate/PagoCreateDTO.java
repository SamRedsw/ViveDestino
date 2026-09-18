package com.example.Dto.DtoCreate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoCreateDTO {

    @NotNull(message = "El ID de la reserva es obligatorio")
    private Long idReserva;

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser un valor mayor a cero")
    private BigDecimal monto; // Usa BigDecimal si en tu entidad Pago lo manejas así

    @NotBlank(message = "El método de pago es obligatorio")
    private String metodoPago; // Ejemplos: "TARJETA_CREDITO", "PSE", "TRANSFERENCIA"
}