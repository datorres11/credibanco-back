package com.credibanco.assessment.card.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransaccionRequestDto {

    @JsonProperty("valorCompra")
    @NotNull
    private Long valorCompra;

    @JsonProperty("direccionCompra")
    @NotNull
    private String direccionCompra;

    @JsonProperty("numReferencia")
    @NotNull
    private Integer numReferencia;

    @JsonProperty("numTarjeta")
    @NotNull
    private Long numTarjeta;

}
