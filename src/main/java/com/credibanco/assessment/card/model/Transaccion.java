package com.credibanco.assessment.card.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Builder
@Data
@Entity
@Table(name = "TRANSACCION")
@NoArgsConstructor
@AllArgsConstructor
public class Transaccion {

    @Id
    @Column(name = "ID_TRANSACCION")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransaccion;

    @Column(name = "VALOR")
    private Long valorCompra;

    @Column(name = "DIRECCION")
    private String direccionCompra;

    @Column(name = "NUM_REFERENCIA")
    private Integer numReferencia;

    @Column(name = "HORA")
    private String hora;

    @ManyToOne(targetEntity = Tarjeta.class)
    private Tarjeta tarjeta;
}
