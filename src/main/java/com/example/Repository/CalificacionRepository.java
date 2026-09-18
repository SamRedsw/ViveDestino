package com.example.Repository;

import com.example.Entity.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {

    List<Calificacion> findByExperiencia_IdExperiencia(Long idExperiencia);
}
