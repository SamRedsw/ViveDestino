package com.example.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "imagenes_experiencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImagenExperiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Long idImagen;

    @NotBlank(message = "La URL de la imagen es obligatoria")
    @Column(name = "url_imagen", nullable = false, length = 255)
    private String urlImagen;

    @Builder.Default
    @Column(nullable = false)
    private Integer orden = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_experiencia", nullable = false)
    private Experiencia experiencia;
}