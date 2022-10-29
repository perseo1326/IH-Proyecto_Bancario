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

    public CreditCard(AccountHolder firstAccountHolder,
                      Optional<AccountHolder> secondAccountholder,
                      String iban,
                      Money balance) {
        super(firstAccountHolder, secondAccountholder, iban, balance);
        this.initCreditCard();
    }

    /*
        RESTRICTIONS:
            1. CreditCard accounts have a default creditLimit of 100 ( X < 100 )
            2. CreditCards may be instantiated with a creditLimit higher than 100 but not higher than 100K (100 < X 100K).
            3. CreditCards have a default interestRate of 0.2 (20%)
            4. CreditCards may be instantiated with an interestRate less than 0.2 but not lower than 0.1(10% < X < 20%)
     */
    public CreditCard(AccountHolder firstAccountHolder,
                      Optional<AccountHolder> secondAccountholder,
                      String iban,
                      Money balance,
                      BigDecimal creditLimit, BigDecimal interesRate) {
        super(firstAccountHolder, secondAccountholder, iban, balance);
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
