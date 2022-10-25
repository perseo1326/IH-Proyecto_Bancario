package com.josko.proyecto_bancario.models;


import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
public class Money {

    private BigDecimal balance;

    private String currency;

    private void initMoney () {
        this.balance = new BigDecimal(0.0);
        this.currency = "EUR";
    }

}
