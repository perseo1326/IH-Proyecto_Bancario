package com.josko.proyecto_bancario.DTOs;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder // -> genera automaticamente constructores
public class ResponseObjectError {

    private int statusCode;
    private String statusType;
    private String errorDescription;


}
