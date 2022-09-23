package com.credibanco.assessment.card.exceptions;

public class BadRequestException extends RuntimeException{
    private static final long serial=1L;

    public Object object;
    public BadRequestException(Object object){
        this.object=object;
    }
}
