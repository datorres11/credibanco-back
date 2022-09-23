package com.credibanco.assessment.card.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.Name;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TarjetaResponseDto {
    private String codigoRespuesta;
    private String mensaje;
    private Integer numeroVerificacion;
    private String pan;
}
