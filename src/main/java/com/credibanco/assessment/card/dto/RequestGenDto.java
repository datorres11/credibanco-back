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
public class RequestGenDto {

    @JsonProperty("numTarjeta")
    @NotNull
    Long numTarjeta;
    @JsonProperty("numeroValidacion")
    @NotNull
    Integer numeroValidacion;
}
