package com.credibanco.assessment.card.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ResponseDto {
    @JsonProperty(value = "Message")
    @NotNull
    private ResponseMessageDto message;

    @JsonProperty(value = "Data")
    private Object object;
}
