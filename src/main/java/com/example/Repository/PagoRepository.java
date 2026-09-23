package com.example.Repository;

import com.example.Entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    Optional<Pago> findByReserva_IdReserva(Long idReserva);

    boolean existsByReferenciaTransaccion(String referenciaTransaccion);

    @Query("SELECT COUNT(p), COALESCE(SUM(p.monto), 0) " +
            "FROM Pago p " +
            "WHERE p.estadoPago = com.example.Entity.Pago.EstadoPago.COMPLETADO " +
            "AND (cast(:fechaInicio as timestamp) IS NULL OR p.fechaPago >= :fechaInicio) " +
            "AND (cast(:fechaFin as timestamp) IS NULL OR p.fechaPago <= :fechaFin)")
    List<Object[]> obtenerMetricasVentas(@Param("fechaInicio") LocalDateTime fechaInicio,
                                         @Param("fechaFin") LocalDateTime fechaFin);
}