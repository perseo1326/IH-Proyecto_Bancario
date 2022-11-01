package com.josko.proyecto_bancario.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Optional;

@Data
@Entity
@Table(name = "credit_card")
public class CreditCard extends BasicAccount {

    @Column(name = "credit_limit", precision = 8, scale = 2)
    private BigDecimal creditLimit;
    @Column(name = "interes_rate", precision = 2, scale = 1)
    private BigDecimal interestRate;

    public CreditCard() {
    }

    public CreditCard(AccountHolder firstAccountHolder,
                      Optional<AccountHolder> secondAccountholder,
                      String iban,
                      Money balance,
                      BigDecimal creditLimit,
                      BigDecimal interestRate) {
        super(firstAccountHolder, secondAccountholder, iban, balance);

        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "\nCreditCard{" + super.toString() +
                "creditLimit=" + creditLimit +
                ", interesRate=" + interestRate +
                '}';
    }


}
