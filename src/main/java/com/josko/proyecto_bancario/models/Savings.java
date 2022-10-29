package com.josko.proyecto_bancario.models;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Entity
@Table(name = "savings")
public class Savings extends Account {

    // default interes rate = 0.25% = 0.0025
    @Transient
    private final BigDecimal INTERES_RATE = new BigDecimal(0.0025);
    // default minimumBalance = 1K
    @Transient
    private final BigDecimal MINIMUM_BALANCE = new BigDecimal(1000);

    @Column(precision = 5, scale = 4)
    private BigDecimal interesRate;

    @Column(precision = 6, scale = 2)
    private BigDecimal minimumBalance;

    public Savings() {
    }

    /*
        constructor with restrictions
     */
    public Savings(AccountHolder firstAccountHolder,
                   Optional<AccountHolder> secondAccountholder,
                   String iban,
                   Money balance,
                   Optional<BigDecimal> interesRate,
                   Optional<BigDecimal> minimumBalance) {
        super(firstAccountHolder, secondAccountholder, iban, balance);

        if (interesRate.isEmpty()) {
            this.interesRate = INTERES_RATE;
        }
        else {
            /*
                RESTRICTION:
                    - Savings accounts have a default interest rate of 0.0025 ( 0.25%)
                    - Savings accounts may be instantiated with an interest rate other than the default, with a maximum interest rate of 0.5 (0.25% < X < 0.5%)
             */
            if ((interesRate.get().compareTo(new BigDecimal(0.0025)) < 0) || interesRate.get().compareTo(new BigDecimal(0.005)) > 0) {
                log.error("PERSISTENCE:SAVINGS:Constructor: The interes rate is not in a valid range.");
                throw new IllegalArgumentException("The interes rate do not meet the request.");
            }
            this.interesRate = interesRate.get();
        }

        if (minimumBalance.isEmpty()) {
            this.minimumBalance = MINIMUM_BALANCE;
        }
        else {
            /*
                RESTRICTION:
                    - Savings accounts should have a default minimumBalance of 1000 (X > 1K)
                    - Savings accounts may be instantiated with a minimum balance of less than 1000 but no lower than 100 ( 100 < X < 1K)
             */
            if (minimumBalance.get().compareTo(new BigDecimal(100)) < 0 || minimumBalance.get().compareTo(new BigDecimal(1000)) > 0) {
                log.error("PERSISTENCE:SAVINGS:Constructor: The minimumBalance is not valid (" + minimumBalance.toString() + ").");
                throw new IllegalArgumentException("The minimumBalance is not in a valid range.");
            }
            this.minimumBalance = minimumBalance.get();
        }
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
