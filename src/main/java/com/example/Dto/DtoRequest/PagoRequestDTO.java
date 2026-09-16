package com.example.Dto.DtoRequest;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoRequestDTO {

    @NotNull(message = "El ID de la reserva es obligatorio")
    private Long idReserva;

    @NotBlank(message = "El método de pago es obligatorio")
    private String metodoPago;

    @NotNull(message = "El monto es obligatorio")
    @Min(value = 1, message = "El monto debe ser positivo")
    private Double monto;

    @NotBlank(message = "La referencia es obligatoria")
    private String referencia;
}