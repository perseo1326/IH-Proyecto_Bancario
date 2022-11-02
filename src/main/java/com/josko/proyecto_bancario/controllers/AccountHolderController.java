package com.josko.proyecto_bancario.controllers;


import com.josko.proyecto_bancario.models.BasicAccount;
import com.josko.proyecto_bancario.services.AccountHolderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/accountholder")
@RequiredArgsConstructor
public class AccountHolderController {

    private final AccountHolderService accountHolderService;

    /*
        Welcome to the AccountHolder user showing all his owned accounts
     */
    @RequestMapping("/welcome")
    @ResponseStatus(HttpStatus.OK)
    public List<BasicAccount> accountHolderWelcome() {

        return accountHolderService.accountHolderWelcome();
    }

    /*
        Show a specific account by his IBAN and owned by the current logged user
     */
    @GetMapping("/{iban}")
    @ResponseStatus(HttpStatus.OK)
    public BasicAccount getAccountByIban(@PathVariable("iban") String iban) {

        return accountHolderService.getAccountByUserAndIban(iban);
    }


}
