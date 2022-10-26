package com.josko.proyecto_bancario.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class AdminController {


    @RequestMapping("/admins")
    @ResponseStatus(HttpStatus.OK)
    public String pruebaHello() {

        return "Hola, Bienvenido a la interfaz bancaria.";
    }

    @PostMapping("/admins")
    @ResponseStatus(HttpStatus.CREATED)
    public String createThirdPartyUser(@RequestBody ) {


    }
}
