package com.credibanco.assessment.card.interceptor;

import com.credibanco.assessment.card.component.ResponseBuilder;
import com.credibanco.assessment.card.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
@RestController
public class ResponseInterceptor implements ResponseBodyAdvice<Object> {

    @Autowired
    ResponseBuilder responseBuilder;


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof ResponseDto){
            return body;
        }else{
            int status = HttpStatus.OK.value();
            if(response instanceof ServletServerHttpResponse){
                status= ((ServletServerHttpResponse) response).getServletResponse().getStatus();
            }
            return responseBuilder.responseBuild(body,HttpStatus.valueOf(status));
        }

    }
}
