package com.josko.proyecto_bancario.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class Prueba {


    @RequestMapping("/")
    @ResponseStatus
    public String pruebaHello() {

        return "Hola, Bienvenido!!";
    }
}
