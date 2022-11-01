package com.josko.proyecto_bancario.DTOs;


import com.josko.proyecto_bancario.models.Money;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Optional;

public class CreditCardDTO {

    private Optional<Long> secondaryOwner;
    @NotBlank(message = "The IBAN cannot be empty.")
    private String iban;

    @NotBlank(message = "A valid balance must be indicated.")
    private Money balance;

    private Optional<BigDecimal> creditLimit;
    private Optional<BigDecimal> creditCardInterestRate;

    public CreditCardDTO(Optional<Long> secondaryOwner, String iban, Money balance, Optional<BigDecimal> creditLimit, Optional<BigDecimal> creditCardInterestRate) {
        this.secondaryOwner = secondaryOwner;
        this.iban = iban;
        this.balance = balance;
        this.creditLimit = creditLimit;
        this.creditCardInterestRate = creditCardInterestRate;
    }

    @Override
    public String toString() {
        return "\nCreditCardDTO{" +
                "secondaryOwner=" + secondaryOwner +
                ", iban='" + iban + '\'' +
                ", balance=" + balance +
                ", creditLimit=" + creditLimit +
                ", creditCardInteresRate=" + creditCardInterestRate +
                '}';
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

    public Optional<BigDecimal> getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Optional<BigDecimal> creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Optional<BigDecimal> getCreditCardInterestRate() {
        return creditCardInterestRate;
    }

    public void setCreditCardInterestRate(Optional<BigDecimal> creditCardInterestRate) {
        this.creditCardInterestRate = creditCardInterestRate;
    }
}
