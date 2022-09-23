package com.credibanco.assessment.card.controller;

import com.credibanco.assessment.card.dto.*;
import com.credibanco.assessment.card.exceptions.BadRequestException;
import com.credibanco.assessment.card.exceptions.InternalServerErrorException;
import com.credibanco.assessment.card.service.ICardAssessmentService;
import com.credibanco.assessment.card.utils.CardAssessmentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin("*")
public class CardAssessmentController {

    @Autowired
    ICardAssessmentService iCardAssessmentService;

    @PostMapping("/tarjeta")
    public ResponseEntity<Object> crearTarjeta(@RequestBody TarjetaRequestDto tarjetaRequestDto) {
        TarjetaResponseDto tarjetaResponseDto;
        try{
            tarjetaResponseDto= iCardAssessmentService.crearTarjeta(tarjetaRequestDto);
            return new ResponseEntity<>(tarjetaResponseDto,HttpStatus.CREATED);
        }catch (InternalServerErrorException e){
            return new ResponseEntity<>(e.object,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/enrolar")
    public ResponseEntity<Object> enrolarTarjeta(@RequestBody RequestGenDto requestGenDto){
        EnrolarResponseDto enrolarResponseDto;
        TarjetaResponseDto tarjetaResponseDto;
        try {
            enrolarResponseDto=iCardAssessmentService.enrolarTarjeta(requestGenDto);
            return new ResponseEntity<>(enrolarResponseDto,HttpStatus.OK);
        }catch (InternalServerErrorException e){
            return new ResponseEntity<>(e.object,HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(HttpClientErrorException.BadRequest e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/tarjeta")
    public ResponseEntity<Object> consultarTarjeta(@RequestParam Long numTarjeta){
        ConsultaTarjetResponseDto consultaTarjetResponseDto;
        consultaTarjetResponseDto = iCardAssessmentService.consultarTarjeta(numTarjeta);
            return new ResponseEntity<>(consultaTarjetResponseDto,HttpStatus.OK);
    }

    @DeleteMapping("/tarjeta")
    public ResponseEntity<Object> eliminarTarjeta(@RequestBody RequestGenDto requestGenDto){
        EliminaTarjetaResponseDto eliminaTarjetaResponseDto;
        try {
            eliminaTarjetaResponseDto= iCardAssessmentService.eliminarTarjeta(requestGenDto);
            return new ResponseEntity<>(eliminaTarjetaResponseDto,HttpStatus.OK);
        }catch (InternalServerErrorException e){
            return new ResponseEntity<>(e.object,HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(HttpClientErrorException.BadRequest e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/transaccion")
    public ResponseEntity<Object> crearTransaccion(@RequestBody TransaccionRequestDto transaccionRequestDto) {
        EstadoResponseDto estadoResponseDto;
        try{
            estadoResponseDto= iCardAssessmentService.crearTransaccion(transaccionRequestDto);
            return new ResponseEntity<>(estadoResponseDto,HttpStatus.CREATED);
        }catch (InternalServerErrorException e){
            return new ResponseEntity<>(e.object,HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(HttpClientErrorException.BadRequest e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/transaccion")
    public ResponseEntity<Object> anularTransaccion(@RequestBody @NotNull AnularTransaccionDto anularTransaccionDto) {
        AnularTransaccionResponse anularTransaccionResponse;
        try{
            anularTransaccionResponse=iCardAssessmentService.anularTransaccion(anularTransaccionDto);
            return new ResponseEntity<>(anularTransaccionResponse,HttpStatus.OK);
        }catch (InternalServerErrorException e){
            return new ResponseEntity<>(e.object,HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(BadRequestException e){
            return new ResponseEntity<>(e.object,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/transacciones")
    public ResponseEntity<Object> consultarTransacciones(@RequestParam Long numTarjeta){
        List<TransacionResponseDto> transacionResponseDto;
        transacionResponseDto = iCardAssessmentService.obtenerTransacciones(numTarjeta);
        return new ResponseEntity<>(transacionResponseDto,HttpStatus.OK);
    }
}
