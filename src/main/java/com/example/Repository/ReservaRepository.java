package com.example.Repository;

import com.example.Dto.ReportesDTO.ExperienciaDemandadaDTO;
import com.example.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByViajero_IdUsuario(Long idUsuario);

    @Query("SELECT e.id, e.nombre, e.ubicacion, COUNT(r), COALESCE(SUM(r.cantidadPersonas), 0) " +
            "FROM Reserva r JOIN r.salida s JOIN s.experiencia e " +
            "WHERE r.estadoReserva != 'CANCELADA' " +
            "GROUP BY e.id, e.nombre, e.ubicacion " +
            "ORDER BY COUNT(r) DESC")
    List<Object[]> obtenerExperienciasMasDemandadas();
}