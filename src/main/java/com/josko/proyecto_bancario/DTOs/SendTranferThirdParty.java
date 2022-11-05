package com.josko.proyecto_bancario.DTOs;


import com.josko.proyecto_bancario.models.Money;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SendTranferThirdParty {

    private final String accountSecretKey;
    private String iban;
    private Money balance;
    private String hasehdKey;



}
