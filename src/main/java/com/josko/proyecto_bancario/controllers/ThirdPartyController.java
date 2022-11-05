package com.josko.proyecto_bancario.controllers;


import com.josko.proyecto_bancario.DTOs.SendTranferThirdParty;
import com.josko.proyecto_bancario.services.ThirdPartyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/thirdparty")
@RequiredArgsConstructor
public class ThirdPartyController {

    private final ThirdPartyService thirdPartyService;

    /*
        Method for bring the welcome to the system at a 'ThirdParty' user
     */
    @RequestMapping("/welcome")
    @ResponseStatus(HttpStatus.OK)
    public String thirdPartyUserWelcome() {

        return thirdPartyService.thirdPartyUserWelcome();
    }

    /*
        Method to perform transferences between a 'ThirdParty' user and an 'AccountHolder'
     */
    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    public String sendTransferToAccountHolder(@Valid @RequestBody SendTranferThirdParty sendTranferThirdParty) {

        return thirdPartyService.sendTransferToAccountHolder(sendTranferThirdParty);
    }

}
