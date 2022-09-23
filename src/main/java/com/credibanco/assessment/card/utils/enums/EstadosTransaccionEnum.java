package com.credibanco.assessment.card.utils.enums;

public enum EstadosTransaccionEnum {
    APROBADA("Aprobada"),
    RECHAZADA("Rechazada"),
    ANUILADA("Anulada");


    public String codigo;

    EstadosTransaccionEnum(String codigo){
        this.codigo=codigo;
    }
}
