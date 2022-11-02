package com.josko.proyecto_bancario.exeptions;

public class SearchWithNoResultsException extends RuntimeException {

    public SearchWithNoResultsException(String message) {
        super(message);
    }


}
