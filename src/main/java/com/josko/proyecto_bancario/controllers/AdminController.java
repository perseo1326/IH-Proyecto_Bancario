package com.josko.proyecto_bancario.controllers;

import com.josko.proyecto_bancario.DTOs.ThirdPartyDTO;
import com.josko.proyecto_bancario.exeptions.NotValidDataException;
import com.josko.proyecto_bancario.models.AccountHolder;
import com.josko.proyecto_bancario.models.BasicAccount;
import com.josko.proyecto_bancario.models.ThirdParty;
import com.josko.proyecto_bancario.models.User;
import com.josko.proyecto_bancario.services.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/admins")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty createThirdPartyUser(@Valid @RequestBody ThirdPartyDTO newThirdPartyDTO) {

        return adminService.createThirdPartyUser(newThirdPartyDTO);
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

    @GetMapping("/admins/accountholders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> getAccountHoldersById(@PathVariable("id") Optional<Long> accountHolderId) {

        if (accountHolderId.isEmpty()) {
            throw new NotValidDataException("SIN VALORES VALIDOS!");
        }

        return adminService.getAccountHolderById(accountHolderId.get());
    }

    @PostMapping("/admins/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public BasicAccount createNewAccount(@RequestBody Map<String, String> values) {

        return adminService.createNewAccount(values);
    }






}
