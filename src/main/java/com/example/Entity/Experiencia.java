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
public class Experiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_experiencia")
    private Long idExperiencia;

    @NotBlank(message = "El título de la experiencia es obligatorio")
    @Column(nullable = false, length = 150)
    private String titulo;

    @NotBlank(message = "La descripción es obligatoria")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;

    @NotBlank(message = "La ubicación es obligatoria")
    @Column(nullable = false, length = 150)
    private String ubicacion;

    @NotNull(message = "Nivel de dificultad es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Dificultad dificultad;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser un valor positivo superior a 0")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @NotNull(message = "Los cupos totales son obligatorios")
    @Min(value = 1, message = "Debe haber al menos 1 cupo disponible")
    @Column(name = "cupos_totales", nullable = false)
    private Integer cuposTotales;

    @Column(name = "cupos_disponibles", nullable = false)
    private Integer cuposDisponibles;

    @NotNull(message = "La duración es obligatoria (en horas o días)")
    @Min(value = 1, message = "La duración mínima debe ser al menos 1")
    @Column(nullable = false)
    private Integer duracion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoExperiencia estado;

    @Column(name = "fecha_publicacion", nullable = false, updatable = false)
    private LocalDateTime fechaPublicacion;

    // Relaciones JPA

    @NotNull(message = "El operador turístico organizador es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_organizador", nullable = false)
    private Usuario organizador;



    @OneToMany(mappedBy = "experiencia", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ImagenExperiencia> imagenes = new ArrayList<>();


    @OneToMany(mappedBy = "experiencia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Salida> salidas = new ArrayList<>();


    @OneToMany(mappedBy = "experiencia", fetch = FetchType.LAZY)
    private List<Calificacion> calificaciones = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.fechaPublicacion = LocalDateTime.now();
        if (this.estado == null) {
            this.estado = EstadoExperiencia.PUBLICADA;
        }
        if (this.cuposDisponibles == null) {
            this.cuposDisponibles = this.cuposTotales;
        }
    }

    // Enums requeridos para la entidad
    public enum Dificultad {
        BAJA, MEDIA, ALTA
    }

    public enum EstadoExperiencia {
        PUBLICADA, DESACTIVADA, ARCHIVADA
    }
}