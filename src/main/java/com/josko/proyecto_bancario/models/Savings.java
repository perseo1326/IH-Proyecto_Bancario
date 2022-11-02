package com.josko.proyecto_bancario.models;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Entity
@Table(name = "savings")
public class Savings extends Account {

    @Column(precision = 5, scale = 4)
    private BigDecimal interestRate;

    @Column(precision = 6, scale = 2)
    private BigDecimal minimumBalance;

    public Savings() {
    }

    public Savings(AccountHolder firstAccountHolder,
                   Optional<AccountHolder> secondAccountholder,
                   String iban,
                   Money balance,
                   BigDecimal interestRate,
                   BigDecimal minimumBalance) {
        super(firstAccountHolder, secondAccountholder, iban, balance);
        this.interestRate = interestRate;
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    @Override
    public String toString() {
        return "Savings{ " + super.toString() +
                "interesRate=" + interestRate +
                ", minimumBalance=" + minimumBalance +
                '}';
    }
}
