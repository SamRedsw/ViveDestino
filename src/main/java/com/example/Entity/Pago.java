package com.example.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "pagos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El valor del pago es obligatorio")
    @Positive(message = "El valor debe ser un monto positivo")
    @Column(nullable = false)
    private Double valor;

    @NotBlank(message = "La referencia de pago es obligatoria")
    @Column(nullable = false, unique = true, length = 100)
    private String referencia;

    @NotBlank(message = "El estado es obligatorio")
    @Column(nullable = false, length = 50)
    private String estado;

    // Relación 1:1 - Un pago pertenece a una única reserva
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserva_id", nullable = false, unique = true)
    private Reserva reserva;
}