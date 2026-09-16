package com.example.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "experiencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Experiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título de la experiencia es obligatorio")
    @Column(nullable = false, length = 100)
    private String titulo;

    @NotBlank(message = "La descripción es obligatoria")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser un valor positivo")
    @Column(nullable = false)
    private Double precio;

    @NotNull(message = "La duración es obligatoria")
    @Min(value = 1, message = "La duración mínima debe ser al menos 1")
    @Column(nullable = false)
    private Integer duracion;

    @Column(length = 100)
    private String ubicacion;
}