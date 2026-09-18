package com.example.Repository;

import com.example.Entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {

    Optional<Pago> findByReserva_IdReserva(Long idReserva);

    boolean existsByReferenciaTransaccion(String referenciaTransaccion);
}