package com.josko.proyecto_bancario.DTOs;


import com.josko.proyecto_bancario.models.Money;

import java.math.BigDecimal;
import java.util.Optional;

public class SavingsDTO {

    private Optional<Long> secondaryOwner;

    private String iban;

    private Optional<BigDecimal> interesRate;

    private Optional<BigDecimal> minimumBalance;

    private Money balance;

    public SavingsDTO(Optional<Long> secondaryOwner, String iban, Money balance, Optional<BigDecimal> interesRate, Optional<BigDecimal> minimumBalance) {
        this.secondaryOwner = secondaryOwner;
        this.iban = iban;
        this.balance = new Money(balance.getAmount(), balance.getCurrency());
        this.interesRate = interesRate;
        this.minimumBalance = minimumBalance;
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
        return interesRate;
    }

    public void setInteresRate(Optional<BigDecimal> interesRate) {
        this.interesRate = interesRate;
    }

    public Optional<BigDecimal> getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Optional<BigDecimal> minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }
}
