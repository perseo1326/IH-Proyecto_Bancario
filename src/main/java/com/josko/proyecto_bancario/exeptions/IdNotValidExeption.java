package com.josko.proyecto_bancario.exeptions;

public class IdNotValidExeption extends RuntimeException {

    public IdNotValidExeption(String id) {

        super("The ID " + id + " is not valid.");
    }
}
