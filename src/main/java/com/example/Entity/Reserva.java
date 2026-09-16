package com.example.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "reservas")
@Data
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del viajero es obligatorio")
    @Column(nullable = false, length = 100)
    private String viajero;

    @NotNull(message = "La fecha de salida es obligatoria")
    @Column(nullable = false)
    private LocalDate salida;

    @NotNull(message = "El número de personas es obligatorio")
    @Min(value = 1, message = "Debe haber al menos 1 persona")
    @Column(nullable = false)
    private Integer personas;

    @NotNull(message = "El total es obligatorio")
    @Positive(message = "El total debe ser un valor positivo")
    @Column(nullable = false)
    private Double total;

    @NotBlank(message = "El estado es obligatorio")
    @Column(nullable = false, length = 50)
    private String estado;

    // Relación N:1 - Muchas reservas pertenecen a un usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Relación 1:1 - Una reserva registra un pago
    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Pago pago;
}