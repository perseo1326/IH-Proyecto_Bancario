package com.josko.proyecto_bancario.models;

import com.josko.proyecto_bancario.enums.AccountStatusEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name = "account")
public class Account extends BasicAccount{


    @Column(columnDefinition = "ENUM('FROZEN', 'ACTIVE')")
    @Enumerated(EnumType.STRING)
    private AccountStatusEnum accountStatusEnum;
    private String secretKey;

    public Account() {
        this.initBasicAccount();
    }

    public Account(String iban, LocalDate creationDate, BigDecimal penaltyFee, AccountHolder firstAccountHolder, Optional<AccountHolder> secondAccountholder) {
        super(iban, creationDate, penaltyFee, firstAccountHolder, secondAccountholder);
        this.initBasicAccount();
    }

    private void initBasicAccount() {
        accountStatusEnum = AccountStatusEnum.ACTIVE;
        this.secretKey = "0000";
    }

    public AccountStatusEnum getAccountStatusEnum() {
        return accountStatusEnum;
    }

    public void setAccountStatusEnum(AccountStatusEnum accountStatusEnum) {
        this.accountStatusEnum = accountStatusEnum;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        if (!secretKey.matches("[0-9]{4}"))
            throw new IllegalArgumentException("The secret key is not valid.");
        this.secretKey = secretKey;
    }

    @Override
    public String toString() {
        return "\nAccount{" + super.toString() +
                "accountStatusEnum=" + accountStatusEnum +
                ", secretKey='" + secretKey + '\'' +
                '}';
    }

}
