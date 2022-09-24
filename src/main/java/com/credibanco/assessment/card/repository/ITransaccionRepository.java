package com.credibanco.assessment.card.repository;

import com.credibanco.assessment.card.model.Tarjeta;
import com.credibanco.assessment.card.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ITransaccionRepository extends JpaRepository<Transaccion,Long> {

    @Query("Select transaccion FROM Transaccion transaccion where transaccion.numReferencia= :numReferencia")
    Optional<Transaccion> findByNumReferencia(Integer numReferencia);

    @Query("Select transaccion FROM Transaccion transaccion where transaccion.tarjeta= :numTarjeta")
    List<Transaccion> findByTarjeta(Tarjeta numTarjeta);
}
