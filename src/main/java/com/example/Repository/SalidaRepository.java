package com.example.Repository;

import com.example.Entity.Salida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface SalidaRepository extends JpaRepository<Salida, Long> {

    List<Salida> findByExperiencia_IdExperiencia(Long idExperiencia);

    List<Salida> findByGuia_IdUsuario(Long idGuia);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Salida s " +
            "WHERE s.guia.idUsuario = :idGuia " +
            "AND s.fechaSalida = :fechaSalida " +
            "AND s.horaSalida = :horaSalida " +
            "AND s.estadoOperativo != com.example.Entity.Salida.EstadoSalida.CANCELADA")
    boolean existeSolapamientoGuia(
            @Param("idGuia") Long idGuia,
            @Param("fechaSalida") LocalDateTime fechaSalida,
            @Param("horaSalida") LocalTime horaSalida
    );
}