package com.credibanco.assessment.card.exceptions;

public class InternalServerErrorException extends RuntimeException{

    private static final long serial=1L;

    public Object object;
    public InternalServerErrorException( Object o){
        this.object=o;
    }
}
