package com.example.Repository;

import com.example.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //parabuscar al usuario por correo al hacer login
    Optional<Usuario> findByCorreo(String correo);
}