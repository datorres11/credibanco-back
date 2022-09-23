package com.credibanco.assessment.card.service;

import com.credibanco.assessment.card.dto.*;

import java.util.List;

public interface ICardAssessmentService {

    public TarjetaResponseDto crearTarjeta(TarjetaRequestDto tarjetaRequestDto);
    public EnrolarResponseDto enrolarTarjeta(RequestGenDto requestGenDto);
    public ConsultaTarjetResponseDto consultarTarjeta(Long numTarjeta);
    public EliminaTarjetaResponseDto eliminarTarjeta(RequestGenDto requestGenDto);
    public EstadoResponseDto crearTransaccion(TransaccionRequestDto transaccionRequestDto);
    public AnularTransaccionResponse anularTransaccion(AnularTransaccionDto anularTransaccionDto);
    public List<TransacionResponseDto> obtenerTransacciones(Long numTarjeta);
}
