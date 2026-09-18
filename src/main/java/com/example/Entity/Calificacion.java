package com.example.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "calificaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calificacion")
    private Long idCalificacion;

    @NotNull(message = "El puntaje es obligatorio")
    @Min(value = 1, message = "El puntaje mínimo es 1")
    @Max(value = 5, message = "El puntaje máximo es 5")
    @Column(nullable = false)
    private Integer puntaje;

    @Column(columnDefinition = "TEXT")
    private String comentario;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    // Relación 1:1 -> Vinculada a una única reserva completada
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reserva", nullable = false, unique = true)
    private Reserva reserva;

    // Relación N:1 -> Viajero que redacta la calificación
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_viajero", nullable = false)
    private Usuario viajero;

    // Relación N:1 -> Experiencia calificada
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_experiencia", nullable = false)
    private Experiencia experiencia;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
    }
}