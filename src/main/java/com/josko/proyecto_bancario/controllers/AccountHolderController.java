package com.josko.proyecto_bancario.controllers;


import com.josko.proyecto_bancario.models.BasicAccount;
import com.josko.proyecto_bancario.services.AccountHolderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/accountholder")
@RequiredArgsConstructor
public class AccountHolderController {

    private final AccountHolderService accountHolderService;

    @RequestMapping("/welcome")
    @ResponseStatus(HttpStatus.OK)
    public List<BasicAccount> accountHolderWelcome() {

        return accountHolderService.accountHolderWelcome();
    }


}
