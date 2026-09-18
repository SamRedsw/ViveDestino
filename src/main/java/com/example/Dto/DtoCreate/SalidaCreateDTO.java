package com.example.Dto.DtoCreate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalidaCreateDTO {

    @NotNull(message = "El ID de la experiencia es obligatorio")
    private Long idExperiencia;

    @NotNull(message = "El ID del guía es obligatorio")
    private Long idGuia;

    @NotNull(message = "La fecha de salida es obligatoria")
    private LocalDateTime fechaSalida;

    @NotNull(message = "La hora de salida es obligatoria")
    private LocalTime horaSalida;

    @NotNull(message = "La fecha de retorno es obligatoria")
    private LocalDate fechaRetorno;

    @NotNull(message = "Los cupos totales son obligatorios")
    @Min(value = 1, message = "Debe haber al menos 1 cupo")
    private Integer cuposTotales;
}