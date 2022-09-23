package com.credibanco.assessment.card.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@Data
@Entity
@Table(name = "TARJETA")
@NoArgsConstructor
@AllArgsConstructor
public class Tarjeta {


    @Column(name = "ID_TARJETA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTarjeta;
    @Id
    @Column(name = "PAN")
    private Long numTarjeta;

    @Column(name = "NUM_VERIFICACION")
    private Integer numVerificaion;

    @Column(name = "NOM_TITULAR")
    private String titular;

    @Column(name = "NUM_IDENTIFICACION")
    private String numIdentificacion;

    @Column(name = "NUM_TELEFONO")
    private Long numTelefono;

    @Column(name = "ESTADO")
    private String estado;
}
