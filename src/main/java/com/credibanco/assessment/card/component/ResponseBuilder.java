package com.credibanco.assessment.card.component;

import com.credibanco.assessment.card.dto.ResponseDto;
import com.credibanco.assessment.card.dto.ResponseMessageDto;
import com.credibanco.assessment.card.dto.StatusDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResponseBuilder {
    public ResponseDto responseBuild(Object response, HttpStatus httpStatus){
        String message = httpStatus.value()==400|| httpStatus.value()==500?response.toString().replace("{","").replace("}",""):"Successfull";
        return ResponseDto.builder().message(ResponseMessageDto.builder()
                                                            .status(StatusDto.builder()
                                                                    .statusCode(httpStatus.value()).build())
                                                                                    .build()).object(response).build();
    }
}
