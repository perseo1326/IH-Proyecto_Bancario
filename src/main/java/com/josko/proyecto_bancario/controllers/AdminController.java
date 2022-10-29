package com.josko.proyecto_bancario.controllers;

import com.josko.proyecto_bancario.DTOs.AccountHolderDTO;
import com.josko.proyecto_bancario.DTOs.SavingDTO;
import com.josko.proyecto_bancario.DTOs.ThirdPartyDTO;
import com.josko.proyecto_bancario.exeptions.NotValidDataException;
import com.josko.proyecto_bancario.models.*;
import com.josko.proyecto_bancario.services.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
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

    /*
        POST: Create a new ThirdParty user.
     */
    @PostMapping("/admins/newthirdparty")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty createThirdPartyUser(@Valid @RequestBody ThirdPartyDTO thirdPartyDTO) {

        return adminService.createThirdPartyUser(thirdPartyDTO);
    }

    /*
        POST: Create a new AccountHolder User
     */
    @PostMapping("/admins/newaccountholder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder createAccountHolderUser(@RequestBody @Valid AccountHolderDTO accountHolderDTO) {

        return adminService.createAccountHolderUser(accountHolderDTO);
    }

    /*
        - Obtener un AccountHolder por su ID.
     */
    @GetMapping("/admins/accountholders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> getAccountHoldersById(@PathVariable("id") Optional<Long> accountHolderId) {

        if (accountHolderId.isEmpty()) {
            throw new NotValidDataException("SIN VALORES VALIDOS!");
        }

        return adminService.getAccountHolderById(accountHolderId.get());
    }

    /*
        - Obtener el listado de todos los AccountHolders
        - Obtener el listado de AccountHolders por nombre.
     */
    @GetMapping("/admins/accountholders")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> getAccountHolders_AndByName(@RequestParam("name") Optional<String> userName) {

        if(userName.isPresent() ) {
            return adminService.getAccountHolderByName(userName.get());
        }

        return adminService.findAllAccountHolders();
    }

    /*
        GET: Realizar busquedas por rango de fechas de nacimiento, y tambien hasta y desde una fecha determinada.
     */
    @GetMapping("/admins/accountholders/birthdate")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> getAccountHoldersByBirthDateRange(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> initialDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> finalDate) {

        return adminService.findByBirthDateValues(initialDate, finalDate);
    }

    /*
        POST: creacion de una cuenta de tipo 'Savings' para el usuario seleccionando
     */
    @PostMapping("/admins/accountholders/{id}/newsavings")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings createNewSavingsAccount(@PathVariable("id") Long userId, @Valid @RequestBody SavingDTO newSavingsDTO) {

        return adminService.createNewSavingsAccount(userId, newSavingsDTO );
    }







}
