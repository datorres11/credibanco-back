package com.credibanco.assessment.card.utils;

import com.credibanco.assessment.card.dto.*;
import com.credibanco.assessment.card.model.Tarjeta;
import com.credibanco.assessment.card.model.Transaccion;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@UtilityClass
public class CardAssessmentUtils {

    public Tarjeta convertirTarjetaRequestDtoATarjetaEntity(TarjetaRequestDto tarjetaRequestDto,String estado, Integer numVerificacion){
        return Tarjeta.builder()
                .numTarjeta(tarjetaRequestDto.getNumTarjeta())
                .estado(estado)
                .numTelefono(tarjetaRequestDto.getNumTelefono())
                .titular(tarjetaRequestDto.getTitular())
                .numIdentificacion(tarjetaRequestDto.getNumIdentificacion())
                .numVerificaion(numVerificacion)
                .tipoTarjeta(tarjetaRequestDto.getTipoTarjeta()).build();
    }

    public TransacionResponseDto convertirTarjetaEntityATransacionResponseDto(Transaccion transaccion){
        return TransacionResponseDto.builder()
                .valorCompra(transaccion.getValorCompra())
                .direccionCompra(transaccion.getDireccionCompra())
                .numReferencia(transaccion.getNumReferencia()).build();
    }

    public Transaccion mapeoDatosTransaccionRquestATransaccionEntity(TransaccionRequestDto transaccionRequestDto,Tarjeta tarjeta){
        return Transaccion.builder()
                .direccionCompra(transaccionRequestDto.getDireccionCompra())
                .numReferencia(transaccionRequestDto.getNumReferencia())
                .valorCompra(transaccionRequestDto.getValorCompra())
                .hora(formatoFecha())
                .tarjeta(tarjeta).build();
    }

    public TarjetaResponseDto convertirTarjetaEntityATarjetaResponseDto(Tarjeta tarjeta, String codigo, String mensaje){
        return TarjetaResponseDto.builder()
                .mensaje(mensaje)
                .pan(enmascararNumeroTarjeta(String.valueOf(tarjeta.getNumTarjeta())))
                .codigoRespuesta(codigo)
                .numeroVerificacion(tarjeta.getNumVerificaion()).build();
    }

    public EnrolarExceptResponseDto mapeoDatosEnrolarExceptResponseDto(String mensaje, Long numeroTarjeta, String codigo){
        return EnrolarExceptResponseDto.builder()
                .mensaje(mensaje)
                .pan(enmascararNumeroTarjeta(String.valueOf(numeroTarjeta)))
                .codigoRespuesta(codigo).build();
    }

    public AnularTransaccionResponse mapeoDatosAnularResponseDto(String mensaje, Integer numeroReferencia, String codigo){
        return AnularTransaccionResponse.builder()
                .mensaje(mensaje)
                .numeroReferencia(numeroReferencia)
                .codigo(codigo).build();
    }

    public EnrolarResponseDto mapeoDatosEnrolarResponseDto(String mensaje, Long numeroTarjeta, String codigo, Integer codigoVerificacion){
        return EnrolarResponseDto.builder()
                .mensaje(mensaje)
                .pan(enmascararNumeroTarjeta(String.valueOf(numeroTarjeta)))
                .codigoRespuesta(codigo)
                .numeroVerificacion(codigoVerificacion).build();
    }

    public ConsultaTarjetResponseDto mapeoDatosConsultaTarjetResponseDto(Tarjeta tarjeta){
        return ConsultaTarjetResponseDto.builder()
               .cedula(tarjeta.getNumIdentificacion())
                .telefono(tarjeta.getNumTelefono())
                .estado(tarjeta.getEstado())
                .titular(tarjeta.getTitular())
                .numeroTarjeta(enmascararNumeroTarjeta(String.valueOf(tarjeta.getNumTarjeta()))).build();
    }

    public EliminaTarjetaResponseDto mapeoDatosEliminaTarjetaResponseDto(String codigo, String mensaje){
        return EliminaTarjetaResponseDto.builder().mensaje(mensaje).codigoRespuesta(codigo).build();
    }

    public EstadoResponseDto mapeoDatosEstadoExceptResponseDto(String codigo, String mensaje, String estado, String numeroReferencia){
        return EstadoResponseDto.builder()
                .mensaje(mensaje)
                .codigoRespuesta(codigo)
                .numeroReferencia(numeroReferencia)
                .estadoTransaccion(estado).build();
    }

    private String enmascararNumeroTarjeta(String numeroTarjeta){
        String enmascarado;
        if(numeroTarjeta.length()==16){
            enmascarado=  numeroTarjeta.substring(0,6)+"******"+numeroTarjeta.substring(12,16);
        }else{
            enmascarado= numeroTarjeta.substring(0,6)+"*********"+numeroTarjeta.substring(13,17);
        }
        return enmascarado;
    }

    public String formatoFecha(){
        LocalTime hora=LocalTime.now(ZoneId.of("Etc/GMT+5"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
       return hora.format(formatter);
    }

}
