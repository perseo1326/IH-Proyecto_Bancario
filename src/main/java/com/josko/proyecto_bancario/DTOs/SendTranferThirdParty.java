package com.josko.proyecto_bancario.DTOs;


import com.josko.proyecto_bancario.models.Money;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
//@RequiredArgsConstructor
public class SendTranferThirdParty {

    @NotBlank(message = "The accountSecretKey is mandatory.")
    private final String accountSecretKey;
    @NotBlank(message = "The IBAN is required.")
    private String iban;
    @Valid
    private Money balance;
    @NotBlank(message = "A hashed key must be provided.")
    private String hasehdKey;


    public SendTranferThirdParty(String accountSecretKey, String iban, Money balance, String hasehdKey) {
        this.accountSecretKey = accountSecretKey;
        this.iban = iban;
        this.balance = balance;
        this.hasehdKey = hasehdKey;
    }
}
