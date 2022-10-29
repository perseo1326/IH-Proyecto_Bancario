package com.josko.proyecto_bancario.DTOs;

import com.josko.proyecto_bancario.models.Money;

import java.math.BigDecimal;
import java.util.Optional;

public class CheckingDTO {


    private Optional<Long> secondaryOwner;
    private String iban;
    private Money balance;
    private Optional<BigDecimal> minimumBalance;
    private Optional<BigDecimal> monthlyMaintenanceFee;

    public CheckingDTO(Optional<Long> secondaryOwner, String iban, Money balance, Optional<BigDecimal> minimumBalance, Optional<BigDecimal> monthlyMaintenanceFee) {
        this.secondaryOwner = secondaryOwner;
        this.iban = iban;
        this.balance = new Money(balance.getAmount(), balance.getCurrency());
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
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

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public Optional<BigDecimal> getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Optional<BigDecimal> minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public Optional<BigDecimal> getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(Optional<BigDecimal> monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }
}
