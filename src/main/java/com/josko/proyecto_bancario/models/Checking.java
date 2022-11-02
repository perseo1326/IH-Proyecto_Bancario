package com.josko.proyecto_bancario.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Optional;

@Entity
@Table(name = "checking")
public class Checking extends Account {

    @NotNull
    @Column(columnDefinition = "decimal(19, 2) DEFAULT 250")
    private BigDecimal minimumBalance;

    @Column(columnDefinition = "decimal(19, 2) not null DEFAULT 12")
    private BigDecimal monthlyMaintenanceFee;

    public Checking() {
    }

    public Checking(AccountHolder firstAccountHolder,
                    Optional<AccountHolder> secondAccountholder,
                    String iban,
                    Money balance, BigDecimal minimumBalance, BigDecimal monthlyMaintenanceFee) {
        super(firstAccountHolder, secondAccountholder, iban, balance);
        this.minimumBalance = minimumBalance;
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
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
