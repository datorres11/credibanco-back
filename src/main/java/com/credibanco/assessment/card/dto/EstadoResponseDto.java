package com.credibanco.assessment.card.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadoResponseDto {

    private String codigoRespuesta;
    private String mensaje;
    private String estadoTransaccion;
    private String numeroReferencia;
}
