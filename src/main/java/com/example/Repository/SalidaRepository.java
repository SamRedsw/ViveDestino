package com.example.Repository;

import com.example.Entity.Salida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalidaRepository extends JpaRepository<Salida, Long> {
    //consultar las salidas programadas para una experiencia
    List<Salida> findByExperienciaId(Long experienciaId);
}