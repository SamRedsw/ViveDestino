package com.example.Repository;

import com.example.Dto.ReportesDTO.ReporteCalificacionesDTO;
import com.example.Entity.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {

    List<Calificacion> findByExperiencia_IdExperiencia(Long idExperiencia);

    @Query("SELECT AVG(c.puntaje) FROM Calificacion c")
    Double obtenerPromedioGeneral();

    @Query("SELECT e.id, e.nombre, AVG(c.puntaje), COUNT(c) " +
            "FROM Calificacion c JOIN c.experiencia e " +
            "GROUP BY e.id, e.nombre " +
            "ORDER BY AVG(c.puntaje) DESC")
    List<Object[]> obtenerPromedioPorExperiencia();
}
