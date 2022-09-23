package com.credibanco.assessment.card.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnularTransaccionDto {
    @JsonProperty("numeroTarjeta")
    private Long numeroTarjeta;

    @JsonProperty("numReferencia")
    private Integer numReferencia;

    @JsonProperty("totalCompra")
    private Long totalCompra;
}
