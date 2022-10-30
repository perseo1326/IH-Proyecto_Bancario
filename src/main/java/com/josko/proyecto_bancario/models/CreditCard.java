package com.josko.proyecto_bancario.models;

import com.josko.proyecto_bancario.services.ValidatorService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Optional;

@Entity
@Table(name = "credit_card")
public class CreditCard extends BasicAccount {

    @Transient
//    @Autowired
    private ValidatorService validatorService;

    @Column(name = "credit_limit", precision = 8, scale = 2)
    private BigDecimal creditLimit;
    @Column(name = "interes_rate", precision = 2, scale = 1)
    private BigDecimal interesRate;

    public CreditCard() {
    }

    public CreditCard(AccountHolder firstAccountHolder,
                      Optional<AccountHolder> secondAccountholder,
                      String iban,
                      Money balance,
                      Optional<BigDecimal> creditLimit,
                      Optional<BigDecimal> interesRate) {
        super(firstAccountHolder, secondAccountholder, iban, balance);

        // TODO:xq 'validatoService' es null???
        var x = validatorService;

        this.creditLimit = validatorService.validateCreditLimit(creditLimit);
        this.interesRate = validatorService.validateCreditCartInteresRate(interesRate);
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
