package com.example.Repository;

import com.example.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    //para consultar las reservas de un usuario especifico
    List<Reserva> findByUsuarioId(Long usuarioId);
}