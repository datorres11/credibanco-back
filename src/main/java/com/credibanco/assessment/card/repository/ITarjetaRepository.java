package com.credibanco.assessment.card.repository;

import com.credibanco.assessment.card.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ITarjetaRepository extends JpaRepository<Tarjeta,Long> {

    @Query("Select tarjeta FROM Tarjeta tarjeta where tarjeta.numTarjeta= :pan")
    Optional<Tarjeta> findByNumTarjeta(Long pan);
}
