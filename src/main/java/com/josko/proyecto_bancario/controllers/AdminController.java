package com.josko.proyecto_bancario.controllers;

import com.josko.proyecto_bancario.DTOs.ThirdPartyDTO;
import com.josko.proyecto_bancario.models.AccountHolder;
import com.josko.proyecto_bancario.models.BasicAccount;
import com.josko.proyecto_bancario.models.ThirdParty;
import com.josko.proyecto_bancario.models.User;
import com.josko.proyecto_bancario.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @RequestMapping("/admins")
    @ResponseStatus(HttpStatus.OK)
    public String adminWelcome() {

        return "Hola, Bienvenido a la interfaz bancaria.";
    }

    @PostMapping("/admins")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty createThirdPartyUser(@Valid @RequestBody ThirdPartyDTO newThirdPartyDTO) {

        return adminService.createThirdPartyUser(newThirdPartyDTO);
    }

    /*
        1. Obtener el listado de todos los accountholders
     */
    @GetMapping("/admins/accountholders")
    @ResponseStatus(HttpStatus.OK)
//    public List<AccountHolder> getAccountHolders(@PathVariable("id") Optional<Long> accountHolderId, @RequestParam("name") Optional<String> userName) {
    public List<AccountHolder> getAccountHolders() {
/*
        if (accountHolderId.isPresent()) {
            return adminService.getAccountHolderById(accountHolderId.get());
        }

        if(userName.isPresent()) {
//            return adminService.getAccountHolderByName(userName.get());
        }
*/
        return adminService.findAllAccountHolders();
    }

    @PostMapping("/admins/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public BasicAccount createNewAccount(@RequestBody Map<String, String> values) {

        return adminService.createNewAccount(values);
    }






}
