package com.example.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Formato de correo inválido")
    @Column(nullable = false, unique = true, length = 100)
    private String correo;

    @NotBlank(message = "El teléfono es obligatorio")
    @Column(nullable = false, length = 20)
    private String telefono;

    @NotBlank(message = "El rol es obligatorio")
    @Column(nullable = false, length = 50)
    private String rol;

    // Relación 1:N - Un usuario puede realizar muchas reservas
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reserva> reservas;
}