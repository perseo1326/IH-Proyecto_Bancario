package com.josko.proyecto_bancario.models;


import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class Money {

    @NotNull(message = "Balance cannot be null.")
    private BigDecimal balance;

    @NotNull(message = "Currency must have a value.")
    private String currency;

    private void initMoney () {
        this.balance = new BigDecimal(0.0);
        this.currency = "EUR";
    }

    public Money() {
        this.initMoney();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "\tMoney{" +
                "balance=" + balance +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return getBalance().equals(money.getBalance()) && getCurrency().equals(money.getCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBalance(), getCurrency());
    }
}
