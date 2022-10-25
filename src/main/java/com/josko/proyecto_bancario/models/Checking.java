package com.josko.proyecto_bancario.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name = "checking")
public class Checking extends Account {


    private BigDecimal minimumBalance;

    private BigDecimal monthlyMaintenanceFee;

    private void initChecking() {
        this.minimumBalance = new BigDecimal(250);
        this.monthlyMaintenanceFee = new BigDecimal(12);
    }

    public Checking() {
        this.initChecking();
    }

    public Checking(String iban, LocalDate creationDate, BigDecimal penaltyFee, AccountHolder firstAccountHolder, Optional<AccountHolder> secondAccountholder) {
        super(iban, creationDate, penaltyFee, firstAccountHolder, secondAccountholder);
        this.initChecking();
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(BigDecimal monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
    }

    @Override
    public String toString() {
        return "\nChecking{ " + super.toString() +
                "minimumBalance=" + minimumBalance +
                ", monthlyMaintenanceFee=" + monthlyMaintenanceFee +
                '}';
    }
}
