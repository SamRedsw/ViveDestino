package com.example.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Long idPago;

    @NotNull(message = "El monto es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto debe ser un valor positivo")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @NotBlank(message = "El método de pago es obligatorio")
    @Column(name = "metodo_pago", nullable = false, length = 50)
    private String metodoPago;

    @NotBlank(message = "La referencia es obligatoria")
    @Column(nullable = false, unique = true, length = 100)
    private String referencia;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_pago", nullable = false, length = 20)
    private EstadoPago estadoPago;

    @Column(name = "fecha_pago", nullable = false, updatable = false)
    private LocalDateTime fechaPago;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reserva", nullable = false, unique = true)
    private Reserva reserva;

    @PrePersist
    public void prePersist() {
        this.fechaPago = LocalDateTime.now();
        if (this.estadoPago == null) {
            this.estadoPago = EstadoPago.COMPLETADO;
        }
    }

    public enum EstadoPago {
        PENDIENTE, COMPLETADO, RECHAZADO, REEMBOLSADO
    }
}