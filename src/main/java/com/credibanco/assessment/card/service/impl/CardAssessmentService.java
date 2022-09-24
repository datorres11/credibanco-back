package com.credibanco.assessment.card.service.impl;

import com.credibanco.assessment.card.repository.ITarjetaRepository;
import com.credibanco.assessment.card.repository.ITransaccionRepository;
import com.credibanco.assessment.card.dto.*;
import com.credibanco.assessment.card.exceptions.BadRequestException;
import com.credibanco.assessment.card.exceptions.InternalServerErrorException;
import com.credibanco.assessment.card.model.Tarjeta;
import com.credibanco.assessment.card.model.Transaccion;
import com.credibanco.assessment.card.service.ICardAssessmentService;
import com.credibanco.assessment.card.utils.CardAssessmentUtils;
import com.credibanco.assessment.card.utils.enums.EstadosTarjetaEnum;
import com.credibanco.assessment.card.utils.enums.EstadosTransaccionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class CardAssessmentService implements ICardAssessmentService {

    @Autowired
    ITarjetaRepository iTarjetaRepository;
    @Autowired
    ITransaccionRepository iTransaccionRepository;

    @Override
    public TarjetaResponseDto crearTarjeta(TarjetaRequestDto tarjetaRequestDto) {
            Random rand = new Random();
            Tarjeta tarjeta = iTarjetaRepository.save(CardAssessmentUtils.convertirTarjetaRequestDtoATarjetaEntity(tarjetaRequestDto, EstadosTarjetaEnum.CREADA.codigo,rand.nextInt(100)));
            TarjetaResponseDto tarjetaResponseDto;
            if(Objects.nonNull(tarjeta)){
                tarjetaResponseDto=CardAssessmentUtils.convertirTarjetaEntityATarjetaResponseDto(tarjeta,"00","Exitoso");
            }else{
                    throw new InternalServerErrorException(CardAssessmentUtils.convertirTarjetaEntityATarjetaResponseDto(tarjeta,"01","Fallido"));
            }
            return tarjetaResponseDto;
    }

    @Override
    public EnrolarResponseDto enrolarTarjeta(RequestGenDto requestGenDto) {
        Optional<Tarjeta> tarjeta=iTarjetaRepository.findByNumTarjeta(requestGenDto.getNumTarjeta());
        EnrolarResponseDto enrolarResponseDto= new EnrolarResponseDto();
        if(tarjeta.isPresent()){
            if(tarjeta.get().getNumVerificaion()==requestGenDto.getNumeroValidacion()){
                tarjeta.get().setEstado(EstadosTarjetaEnum.ENROLADA.codigo);
                iTarjetaRepository.save(tarjeta.get());
                enrolarResponseDto=CardAssessmentUtils.mapeoDatosEnrolarResponseDto("Éxito",requestGenDto.getNumTarjeta(),"00",requestGenDto.getNumeroValidacion());
            }else{
                throw new InternalServerErrorException(CardAssessmentUtils.mapeoDatosEnrolarExceptResponseDto("Número de validación invalido",requestGenDto.getNumTarjeta(),"02"));
            }
        }else{
            throw new InternalServerErrorException(CardAssessmentUtils.mapeoDatosEnrolarExceptResponseDto("Tarjeta no existe",requestGenDto.getNumTarjeta(),"01"));
        }
        return enrolarResponseDto;
    }

    @Override
    public ConsultaTarjetResponseDto consultarTarjeta(Long numTarjeta) {
        Optional<Tarjeta> tarjeta=iTarjetaRepository.findByNumTarjeta(numTarjeta);
        ConsultaTarjetResponseDto consultaTarjetResponseDto= CardAssessmentUtils.mapeoDatosConsultaTarjetResponseDto(tarjeta.get());
        return consultaTarjetResponseDto;
    }

    @Override
    public EliminaTarjetaResponseDto eliminarTarjeta(RequestGenDto requestGenDto) {
        Optional<Tarjeta> tarjeta=iTarjetaRepository.findByNumTarjeta(requestGenDto.getNumTarjeta());
        EliminaTarjetaResponseDto eliminaTarjetaResponseDto = new EliminaTarjetaResponseDto();
        if(tarjeta.isPresent()){
            iTarjetaRepository.delete(tarjeta.get());
            if(iTarjetaRepository.findByNumTarjeta(requestGenDto.getNumTarjeta()).isPresent()) {
                throw new InternalServerErrorException(CardAssessmentUtils.mapeoDatosEliminaTarjetaResponseDto("01", "No se ha eliminado la tarjeta"));
            }else{
                eliminaTarjetaResponseDto = CardAssessmentUtils.mapeoDatosEliminaTarjetaResponseDto("00", "Se ha eliminado la tarjeta");
            }
        }
        return eliminaTarjetaResponseDto;
    }

    @Override
    public EstadoResponseDto crearTransaccion(TransaccionRequestDto transaccionRequestDto) {
        Optional<Tarjeta> tarjeta=iTarjetaRepository.findByNumTarjeta(transaccionRequestDto.getNumTarjeta());
        EstadoResponseDto estadoResponseDto = new EstadoResponseDto();
        Random rand = new Random();
        if(tarjeta.isPresent()){
            if(tarjeta.get().getEstado().equals(EstadosTarjetaEnum.ENROLADA.codigo)){
                estadoResponseDto =CardAssessmentUtils.mapeoDatosEstadoExceptResponseDto("00","Compra Existosa", EstadosTransaccionEnum.APROBADA.codigo, String.valueOf(transaccionRequestDto.getNumReferencia()));
                iTransaccionRepository.save(CardAssessmentUtils.mapeoDatosTransaccionRquestATransaccionEntity(transaccionRequestDto,tarjeta.get()));
            }else{
                throw new InternalServerErrorException(CardAssessmentUtils.mapeoDatosEstadoExceptResponseDto("02","Tarjeta no enrolada",EstadosTransaccionEnum.RECHAZADA.codigo, String.valueOf(transaccionRequestDto.getNumReferencia())));
            }
        }else{
            throw new InternalServerErrorException(CardAssessmentUtils.mapeoDatosEstadoExceptResponseDto("01","Tarjeta no existe",EstadosTransaccionEnum.RECHAZADA.codigo,String.valueOf(transaccionRequestDto.getNumReferencia())));
        }
        return estadoResponseDto;
    }

    @Override
    public AnularTransaccionResponse anularTransaccion(AnularTransaccionDto anularTransaccionDto) {
        AnularTransaccionResponse anularTransaccionResponse= new AnularTransaccionResponse();
        if(String.valueOf(anularTransaccionDto.getNumReferencia()).length()==6 && String.valueOf(anularTransaccionDto.getNumReferencia()).matches("[0-9]*")){
            Optional<Transaccion> transaccion=iTransaccionRepository.findByNumReferencia(anularTransaccionDto.getNumReferencia());
            LocalTime horaTransRealizada= LocalTime.parse(transaccion.get().getHora());
            LocalTime horaActual=LocalTime.parse(CardAssessmentUtils.formatoFecha());
            if(((int) ChronoUnit.MINUTES.between(horaTransRealizada, horaActual))>=5){
                throw new BadRequestException(CardAssessmentUtils.mapeoDatosAnularResponseDto("No se puede anular la transacción",anularTransaccionDto.getNumReferencia(),"02"));
            }else{
                iTransaccionRepository.delete(transaccion.get());
                anularTransaccionResponse= CardAssessmentUtils.mapeoDatosAnularResponseDto("Compra anulada",anularTransaccionDto.getNumReferencia(),"00");
            }
        }else{
            throw new BadRequestException(CardAssessmentUtils.mapeoDatosAnularResponseDto("Numero de referencia invalido",anularTransaccionDto.getNumReferencia(),"01"));
        }
        return anularTransaccionResponse;
    }

    @Override
    public List<TransacionResponseDto> obtenerTransacciones(Long numTarjeta) {
        Optional<Tarjeta> tarjeta=iTarjetaRepository.findByNumTarjeta(numTarjeta);
        List<Transaccion> transacciones=iTransaccionRepository.findByTarjeta(tarjeta.get());
        List<TransacionResponseDto> transacionResponseDtos = new ArrayList<>();
        for(Transaccion transaccion: transacciones){
            transacionResponseDtos.add(CardAssessmentUtils.convertirTarjetaEntityATransacionResponseDto(transaccion));
        }
        return transacionResponseDtos;
    }
}
