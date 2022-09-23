package com.credibanco.assessment.card.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacionResponseDto {

    private Long valorCompra;

    private String direccionCompra;

    private Integer numReferencia;
}
