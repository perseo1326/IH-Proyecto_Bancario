package com.josko.proyecto_bancario.controllers;


import com.josko.proyecto_bancario.services.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/accountholder")
@RequiredArgsConstructor
public class AccountHolderController {

    private final AdminService adminService;

    @RequestMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public String accountHolderWelcome() {

        return "Hola, Bienvenido ACCOUNTHOLDER a la interfaz bancaria.";
    }



}
