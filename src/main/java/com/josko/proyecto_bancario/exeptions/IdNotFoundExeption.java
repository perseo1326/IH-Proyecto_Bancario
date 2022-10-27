package com.josko.proyecto_bancario.exeptions;


public class IdNotFoundExeption extends RuntimeException {

    public IdNotFoundExeption(String id) {
        super("The ID " + id + " was not found.");
    }

    public IdNotFoundExeption(String message, boolean useMessage) {
        super(message);
    }

    public IdNotFoundExeption() {
        super("The supplied ID was not found.");
    }

}
