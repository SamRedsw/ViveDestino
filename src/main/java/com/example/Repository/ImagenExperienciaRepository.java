package com.example.Repository;

import com.example.Entity.ImagenExperiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagenExperienciaRepository extends JpaRepository<ImagenExperiencia, Long> {

    //tener todas las imageens de una experiencia ordenadas por psicion
    List<ImagenExperiencia> findByExperienciaIdOrderByOrdenAsc(Long experienciaId);
}