package com.example.Dto.DtoCreate;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Schema(description = "Datos para la creación de una salida programada")
public class SalidaCreateDTO {

    @NotNull
    @Schema(description = "ID de la experiencia asociada", example = "15")
    private Long idExperiencia;

    @NotNull
    @Schema(description = "ID del guía asignado", example = "4")
    private Long idGuia;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Schema(type = "string", description = "Fecha y hora de salida", example = "2026-09-23T01:04:51")
    private LocalDateTime fechaSalida;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(type = "string", description = "Hora específica de salida", example = "11:10:10")
    private LocalTime horaSalida;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(type = "string", description = "Fecha estimada de retorno", example = "2026-09-23")
    private LocalDate fechaRetorno;

    @NotNull
    @Schema(description = "Número de cupos totales disponibles", example = "10")
    private Integer cuposTotales;
}