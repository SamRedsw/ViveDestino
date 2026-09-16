package com.example.Dto.DtoCreate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalidaCreateDTO {

    @NotNull(message = "El ID de la experiencia es obligatorio")
    private Long idExperiencia;

    @NotNull(message = "El ID del guía es obligatorio")
    private Long idGuia;

    @NotNull(message = "La fecha y hora son obligatorias")
    @Future(message = "La fecha debe ser futura")
    private LocalDateTime fechaHora;

    @NotNull(message = "El cupo máximo es obligatorio")
    @Min(value = 1, message = "Debe haber al menos 1 cupo")
    private Integer cupoMaximo;
}