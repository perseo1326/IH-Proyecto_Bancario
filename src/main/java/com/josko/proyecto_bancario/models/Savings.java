package com.josko.proyecto_bancario.models;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Entity
@Table(name = "savings")
public class Savings extends Account {

    private BigDecimal interesRate;

    private BigDecimal minimumBalance;


    public Savings() {
        super();
        this.initSavings();
    }

    public Savings(String iban, LocalDate creationDate, BigDecimal penaltyFee, AccountHolder firstAccountHolder, Optional<AccountHolder> secondAccountholder) {
        super(iban, creationDate, penaltyFee, firstAccountHolder, secondAccountholder);
        this.initSavings();
    }

    public Savings(String iban, LocalDate creationDate, BigDecimal penaltyFee, AccountHolder firstAccountHolder, Optional<AccountHolder> secondAccountholder, BigDecimal interesRate, BigDecimal minimumBalance) {
        super(iban, creationDate, penaltyFee, firstAccountHolder, secondAccountholder);

        if ((interesRate.compareTo(new BigDecimal(0.0025)) < 0) || interesRate.compareTo(new BigDecimal(0.005)) > 0 ) {
            log.error("PERSISTENCE:SAVINGS:Constructor: The interes rate is not in a valid range.");
            throw new IllegalArgumentException("The interes rate do not meet the request.");
        }

        if (minimumBalance.compareTo(new BigDecimal(100)) < 0 || minimumBalance.compareTo(new BigDecimal(1000)) > 0 ) {
            log.error("PERSISTENCE:SAVINGS:Constructor: The minimumBalance is not valid (" + minimumBalance.toString() + ").");
            throw new IllegalArgumentException("The minimumBalance is not in a valid range.");
        }

        this.interesRate = interesRate;
    }

    private void initSavings() {
        // default interes rate = 0.25% = 0.0025
        this.interesRate = new BigDecimal(0.0025);
        // default minimumBalance = 1K
        this.minimumBalance = new BigDecimal(1000);
    }

    public BigDecimal getInteresRate() {
        return interesRate;
    }

    public void setInteresRate(BigDecimal interesRate) {
        this.interesRate = interesRate;
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
                "interesRate=" + interesRate +
                ", minimumBalance=" + minimumBalance +
                '}';
    }
}
