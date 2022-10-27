package com.josko.proyecto_bancario.exeptions;

public class StatusNotValidExeption extends RuntimeException {

    public StatusNotValidExeption(String status) {
        super("The status " + status + " is not a valid selection.");
    }

}
