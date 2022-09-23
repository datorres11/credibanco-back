package com.credibanco.assessment.card.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarjetaRequestDto {

    @JsonProperty("numTarjeta")
    @NotNull
    private Long numTarjeta;

    @JsonProperty("titular")
    @NotNull
    private String titular;

    @JsonProperty("numIdentificacion")
    @NotNull
    private String numIdentificacion;

    @JsonProperty("numTelefono")
    @NotNull
    private Long numTelefono;

    @JsonProperty("tipoTarjeta")
    @NotNull
    private String tipoTarjeta;

}
