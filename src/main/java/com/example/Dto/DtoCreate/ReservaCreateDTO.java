package com.example.Dto.DtoCreate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservaCreateDTO {

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long idUsuario;

    @NotNull(message = "El ID de la salida es obligatorio")
    private Long idSalida;

    @NotNull(message = "La cantidad de personas es obligatoria")
    @Min(value = 1, message = "Debe reservar al menos para 1 persona")
    private Integer cantidadPersonas;
}