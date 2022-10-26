package com.josko.proyecto_bancario.controllers;

import com.josko.proyecto_bancario.DTOs.ThirdPartyDTO;
import com.josko.proyecto_bancario.models.ThirdParty;
import com.josko.proyecto_bancario.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @RequestMapping("/admins")
    @ResponseStatus(HttpStatus.OK)
    public String pruebaHello() {

        return "Hola, Bienvenido a la interfaz bancaria.";
    }

    @PostMapping("/admins")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty createThirdPartyUser(@Valid @RequestBody ThirdPartyDTO newThirdPartyDTO) {

        return adminService.createThirdPartyUser(newThirdPartyDTO);

    }
}
