package com.josko.proyecto_bancario.models;

import com.josko.proyecto_bancario.enums.AccountStatusEnum;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "accounts")
public class Account extends BasicAccount{


    @Column(columnDefinition = "ENUM('FROZEN', 'ACTIVE')")
    @Enumerated(EnumType.STRING)
    private AccountStatusEnum accountStatusEnum;
    private String secretKey;

    public Account() {
    }

    public Account(AccountHolder firstAccountHolder,
                   Optional<AccountHolder> secondAccountholder,
                   String iban,
                   Money balance) {
        super(firstAccountHolder, secondAccountholder, iban, balance);
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

    // TODO: implementar las restricciones en el servicio o duplicarlas aqui tambien??
    public void setSecretKey(String secretKey) {
        if (!secretKey.matches("[0-9]{4}"))
            throw new IllegalArgumentException("The secret key is not valid.");
        this.secretKey = secretKey;
    }

    @Override
    public String toString() {
        return "\n\tAccount{" + super.toString() +
                "accountStatusEnum=" + accountStatusEnum +
                ", secretKey='" + secretKey + '\'' +
                '}';
    }

}
