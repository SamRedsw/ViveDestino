package com.example.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "salidas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Salida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_salida")
    private Long idSalida;

    @NotNull(message = "La fecha de salida es obligatoria")
    @Column(name = "fecha_salida", nullable = false)
    private LocalDateTime fechaSalida;

    @NotNull(message = "La hora de salida es obligatoria")
    @Column(name = "hora_salida", nullable = false)
    private LocalTime horaSalida;

    @NotNull(message = "La fecha de retorno es obligatoria")
    @Column(name = "fecha_retorno", nullable = false)
    private LocalDate fechaRetorno;

    @NotNull(message = "Los cupos totales son obligatorios")
    @Min(value = 1, message = "Debe haber al menos 1 cupo")
    @Column(name = "cupos_totales", nullable = false)
    private Integer cuposTotales;

    @Column(name = "cupos_disponibles", nullable = false)
    private Integer cuposDisponibles;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_operativo", nullable = false, length = 30)
    private EstadoOperativo estadoOperativo;

    @Column(name = "motivo_cancelacion", columnDefinition = "TEXT")
    private String motivoCancelacion;

    // Relación N:1 -> A qué Experiencia pertenece esta salida
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_experiencia", nullable = false)
    private Experiencia experiencia;

    // Relación N:1 -> Guía asignado a esta salida
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_guia", nullable = false)
    private Usuario guia;

    // Relación 1:N -> Reservas realizadas para esta fecha específica
    @OneToMany(mappedBy = "salida", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reserva> reservas = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (this.estadoOperativo == null) {
            this.estadoOperativo = EstadoOperativo.PROGRAMADA;
        }
        if (this.cuposDisponibles == null) {
            this.cuposDisponibles = this.cuposTotales;
        }
    }

    public enum EstadoOperativo {
        PROGRAMADA, EN_CURSO, COMPLETADA, CANCELADA, REPROGRAMADA
    }
}