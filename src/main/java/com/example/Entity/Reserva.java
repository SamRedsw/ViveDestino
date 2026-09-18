package com.example.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long idReserva;

    @NotNull(message = "El número de personas es obligatorio")
    @Min(value = 1, message = "Debe haber al menos 1 persona")
    @Column(name = "cantidad_personas", nullable = false)
    private Integer cantidadPersonas;

    @NotNull(message = "El total es obligatorio")
    @Positive(message = "El total debe ser un valor positivo")
    @Column(name = "total_pagar", nullable = false)
    private Double totalPagar;

    @NotBlank(message = "El estado es obligatorio")
    @Column(name = "estado_reserva", nullable = false, length = 50)
    private String estadoReserva;

    @Column(name = "fecha_reserva", nullable = false, updatable = false)
    private LocalDateTime fechaReserva;

    @Column(nullable = false)
    private Boolean asistio;

    // Relación N:1 -> Usuario (Viajero que realiza la reserva)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    // Relación N:1 -> Salida programada (Entidad Salida, NO LocalDate)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_salida", nullable = false)
    private Salida salida;

    // Relación 1:1 -> Pago asociado a la reserva
    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Pago pago;

    // Relación 1:1 -> Calificación registrada post-experiencia
    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Calificacion calificacion;

    @PrePersist
    public void prePersist() {
        this.fechaReserva = LocalDateTime.now();
        if (this.asistio == null) {
            this.asistio = false;
        }
    }
}