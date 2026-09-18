package com.example.Dto;

import com.example.Entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

    private Long idUsuario;
    private String nombre;
    private String correo;
    private String telefono;
    private Usuario.RolUsuario rol;
    private String fotoPerfil;
    private String estado;
    private LocalDateTime fechaRegistro;
}