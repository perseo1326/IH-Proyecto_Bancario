package com.josko.proyecto_bancario.DTOs;


import com.josko.proyecto_bancario.models.Money;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class ChangeBalance {

    @NotBlank(message = "A Valid IBAN must be given.")
    private String iban;

    @NotNull(message = "The balance must contain 'currency' and 'amount' and .")
    private Money balance;

}
