package com.credibanco.assessment.card.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaTarjetResponseDto {

    private String titular;
    private String cedula;
    private Long telefono;
    private String estado;
    private String numeroTarjeta;
}
