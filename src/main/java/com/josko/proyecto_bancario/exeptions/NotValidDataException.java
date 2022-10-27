package com.josko.proyecto_bancario.exeptions;


public class NotValidDataException extends  RuntimeException {

    public NotValidDataException(String message) {
        super(message);
    }

    public NotValidDataException() {
        super();
    }
}
