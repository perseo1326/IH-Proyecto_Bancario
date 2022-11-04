package com.josko.proyecto_bancario.DTOs;

import com.josko.proyecto_bancario.models.Money;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@RequiredArgsConstructor
public class TransferAccountHolder {

    @NotBlank(message = "An account name is mandatory.")
    private final String accountName;

    @NotBlank(message = "An IBAN must be provided.")
    private final String ibanDestination;

    @Valid
    private final Money balance;



}
