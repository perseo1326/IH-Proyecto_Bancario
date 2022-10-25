package com.josko.proyecto_bancario.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name = "credit_card")
public class CreditCard extends BasicAccount {

    private BigDecimal creditLimit;

    private BigDecimal interesRate;

    public CreditCard() {
        this.initCreditCard();
    }

    public CreditCard(String iban, LocalDate creationDate, BigDecimal penaltyFee, AccountHolder firstAccountHolder, Optional<AccountHolder> secondAccountholder) {
        super(iban, creationDate, penaltyFee, firstAccountHolder, secondAccountholder);
        this.initCreditCard();
    }

    public CreditCard(String iban, LocalDate creationDate, BigDecimal penaltyFee, AccountHolder firstAccountHolder, Optional<AccountHolder> secondAccountholder, BigDecimal creditLimit, BigDecimal interesRate) {
        super(iban, creationDate, penaltyFee, firstAccountHolder, secondAccountholder);
        // if credit limit fit -> 100 < X < 100K

        if (creditLimit.compareTo(new BigDecimal(100)) < 0 || creditLimit.compareTo(new BigDecimal(100_000)) > 0 ) {
            throw new IllegalArgumentException("The credit limit is not valid.");
        }

        if (interesRate.compareTo(new BigDecimal(0.1)) < 0 || interesRate.compareTo(new BigDecimal(0.2)) > 0) {
            throw new IllegalArgumentException("The interes rate is not valid.");
        }

        this.creditLimit = creditLimit;
        this.interesRate = interesRate;
    }

    private void initCreditCard() {
        // default credit limit = 100
        this.creditLimit = new BigDecimal(100);
        // default interes rate = 20%
        this.interesRate = new BigDecimal(0.2);
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInteresRate() {
        return interesRate;
    }

    public void setInteresRate(BigDecimal interesRate) {
        this.interesRate = interesRate;
    }

    @Override
    public String toString() {
        return "\nCreditCard{" + super.toString() +
                "creditLimit=" + creditLimit +
                ", interesRate=" + interesRate +
                '}';
    }


}
