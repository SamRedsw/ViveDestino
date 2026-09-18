package com.example.Repository;

import com.example.Entity.ImagenExperiencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagenExperienciaRepository extends JpaRepository<ImagenExperiencia, Long> {
    List<ImagenExperiencia> findByExperiencia_IdExperiencia(Long idExperiencia);
}
