package com.example.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Formato de correo inválido")
    @Column(nullable = false, unique = true, length = 150)
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    @Column(name = "password_hash", nullable = false)
    private String password;

    @Column(length = 20)
    private String telefono;

    @NotNull(message = "El rol es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private RolUsuario rol;

    @Column(length = 255)
    private String fotoPerfil;

    @Builder.Default
    @Column(nullable = false, length = 20)
    private String estado = "ACTIVO";

    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDateTime fechaRegistro;

    @Builder.Default
    @OneToMany(mappedBy = "organizador", fetch = FetchType.LAZY)
    private List<Experiencia> experienciasOrganizadas = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "guia", fetch = FetchType.LAZY)
    private List<Salida> salidasAsignadas = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "viajero", fetch = FetchType.LAZY)
    private List<Reserva> reservas = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "viajero", fetch = FetchType.LAZY)
    private List<Calificacion> calificaciones = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.fechaRegistro = LocalDateTime.now();
        if (this.estado == null) {
            this.estado = EstadoUsuario.ACTIVO;
        }
    }

    public enum Rol {
        VIAJERO, OPERADOR, GUIA, ADMIN
    }

    @OneToMany(mappedBy = "organizador", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Experiencia> experiencias;

    public enum RolUsuario {
        VIAJERO,
        GUIA,
        ADMINISTRADOR,
        OPERADOR
    }
}