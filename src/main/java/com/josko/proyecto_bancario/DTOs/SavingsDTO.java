package com.josko.proyecto_bancario.DTOs;


import com.josko.proyecto_bancario.models.Money;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Optional;

public class SavingsDTO {

    private Optional<Long> secondaryOwner;
    @NotBlank(message = "The IBAN cannot be empty.")
    private String iban;

    @NotBlank(message = "A valid balance must be indicated.")
    private Money balance;

    private Optional<BigDecimal> interestRate;

    private Optional<BigDecimal> minimumBalanceSavings;


    public SavingsDTO(Optional<Long> secondaryOwner, String iban, Money balance, Optional<BigDecimal> interesRate, Optional<BigDecimal> minimumBalanceSavings) {
        this.secondaryOwner = secondaryOwner;
        this.iban = iban;
        this.balance = new Money(balance.getAmount(), balance.getCurrency());
        this.interestRate = interesRate;
        this.minimumBalanceSavings = minimumBalanceSavings;
    }

    public Optional<Long> getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(Optional<Long> secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Optional<BigDecimal> getInteresRate() {
        return interestRate;
    }

    public void setInteresRate(Optional<BigDecimal> interesRate) {
        this.interestRate = interesRate;
    }

    public Optional<BigDecimal> getMinimumBalance() {
        return minimumBalanceSavings;
    }

    public void setMinimumBalance(Optional<BigDecimal> minimumBalance) {
        this.minimumBalanceSavings = minimumBalance;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }
}
