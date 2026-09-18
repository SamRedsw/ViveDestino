package com.example.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
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

    @NotNull(message = "La cantidad de personas es obligatoria")
    @Min(value = 1, message = "Debe reservar al menos para 1 persona")
    @Column(name = "cantidad_personas", nullable = false)
    private Integer cantidadPersonas;

    @NotNull(message = "El total a pagar es obligatorio")
    @Column(name = "total_pagar", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPagar;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_reserva", nullable = false, length = 20)
    private EstadoReserva estadoReserva;

    @Column(name = "fecha_reserva", nullable = false, updatable = false)
    private LocalDateTime fechaReserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_viajero", nullable = false)
    private Usuario viajero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_salida", nullable = false)
    private Salida salida;

    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Pago pago;

    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Calificacion calificacion;

    @PrePersist
    public void prePersist() {
        this.fechaReserva = LocalDateTime.now();
        if (this.estadoReserva == null) {
            this.estadoReserva = EstadoReserva.PENDIENTE;
        }
    }

    public enum EstadoReserva {
        PENDIENTE, CONFIRMADA, CANCELADA, COMPLETADA
    }
}