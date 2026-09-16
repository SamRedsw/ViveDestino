package com.example.Repository;

import com.example.Entity.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {
    //oobtener todas las opiniones de una experiencia en especifico
    List<Calificacion> findByExperienciaId(Long experienciaId);
}