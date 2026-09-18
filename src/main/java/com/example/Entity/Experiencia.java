package com.example.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "experiencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Experiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_experiencia")
    private Long idExperiencia;

    @NotBlank(message = "El nombre de la experiencia es obligatorio")
    @Column(nullable = false, length = 150)
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @NotBlank(message = "La categoría es obligatoria")
    @Column(nullable = false, length = 50)
    private String categoria;

    @NotBlank(message = "La ubicación es obligatoria")
    @Column(nullable = false, length = 150)
    private String ubicacion;

    @NotNull(message = "El nivel de dificultad es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Dificultad dificultad;

    @NotNull(message = "El precio base es obligatorio")
    @DecimalMin(value = "0.00", message = "El precio debe ser un valor positivo")
    @Column(name = "precio_base", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioBase;

    @Column(columnDefinition = "TEXT")
    private String requisitos;

    @Column(name = "politica_cancelacion", columnDefinition = "TEXT")
    private String politicaCancelacion;

    @Column(length = 50)
    private String duracion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoExperiencia estado;

    @Column(name = "fecha_publicacion", nullable = false, updatable = false)
    private LocalDateTime fechaPublicacion;

    @NotNull(message = "El operador organizador es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_organizador", nullable = false)
    private Usuario organizador;

    @Builder.Default
    @OneToMany(mappedBy = "experiencia", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ImagenExperiencia> imagenes = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "experiencia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Salida> salidas = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "experiencia", fetch = FetchType.LAZY)
    private List<Calificacion> calificaciones = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.fechaPublicacion = LocalDateTime.now();
        if (this.estado == null) {
            this.estado = EstadoExperiencia.ACTIVA;
        }
    }

    public enum Dificultad {
        BAJA, MEDIA, ALTA
    }

    public enum EstadoExperiencia {
        ACTIVA, INACTIVA, ARCHIVADA
    }
}