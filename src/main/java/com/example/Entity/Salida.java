package com.example.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "salidas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Salida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_salida")
    private Long idSalida;

    @NotNull(message = "La fecha y hora de salida son obligatorias")
    @Future(message = "La fecha de salida debe ser futura")
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;

    @NotNull(message = "El cupo máximo es obligatorio")
    @Min(value = 1, message = "Debe haber al menos 1 cupo")
    @Column(name = "cupo_maximo", nullable = false)
    private Integer cupoMaximo;

    @Column(name = "cupos_disponibles", nullable = false)
    private Integer cuposDisponibles;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_operativo", nullable = false, length = 20)
    private EstadoSalida estadoOperativo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_experiencia", nullable = false)
    private Experiencia experiencia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_guia", nullable = false)
    private Usuario guia;

    @Builder.Default
    @OneToMany(mappedBy = "salida", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reserva> reservas = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (this.cuposDisponibles == null) {
            this.cuposDisponibles = this.cupoMaximo;
        }
        if (this.estadoOperativo == null) {
            this.estadoOperativo = EstadoSalida.PROGRAMADA;
        }
    }

    public enum EstadoSalida {
        PROGRAMADA, EN_CURSO, FINALIZADA, CANCELADA
    }
}