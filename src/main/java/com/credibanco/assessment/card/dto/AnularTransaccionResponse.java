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
public class AnularTransaccionResponse {
    private String codigo;
    private String mensaje;
    private Integer numeroReferencia;
}
