package com.josko.proyecto_bancario.models;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Entity
@Table(name = "basic_account")
@Inheritance(strategy = InheritanceType.JOINED)
public class BasicAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "basic_account_id", nullable = false)
    private Long id;

    @NotNull(message = "IBAN cannot be null.")
    private String iban;

    @NotNull(message = "The birthdate cannot be null.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    private BigDecimal penaltyFee;

    @Embedded
    private Money money;

    @OneToOne
    @JoinColumn(name = "first_account_holder_user_id")
    private AccountHolder firstAccountHolder;
    @OneToOne
    @JoinColumn(name = "second_accountholder_user_id")
    private AccountHolder secondAccountholder;

    public BasicAccount() {
    }

    public BasicAccount(String iban, LocalDate creationDate, BigDecimal penaltyFee, AccountHolder firstAccountHolder, Optional<AccountHolder> secondAccountholder) {
        this.iban = iban;
        this.creationDate = creationDate;
        this.penaltyFee = penaltyFee;
        this.firstAccountHolder = firstAccountHolder;
        this.secondAccountholder = secondAccountholder.get();
    }

    public Long getId() {
        return id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public AccountHolder getFirstAccountHolder() {
        return firstAccountHolder;
    }

    public void setFirstAccountHolder(AccountHolder firstAccountHolder) {
        this.firstAccountHolder = firstAccountHolder;
    }

    public AccountHolder getSecondAccountholder() {
        return secondAccountholder;
    }

    public void setSecondAccountholder(AccountHolder secondAccountholder) {
        this.secondAccountholder = secondAccountholder;
    }

    @Override
    public String toString() {
        return "BasicAccount{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                ", creationDate=" + creationDate +
                ", penaltyFee=" + penaltyFee +
//                ", firstAccountHolder=" + firstAccountHolder +
//                ", secondAccountholder=" + secondAccountholder +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicAccount that = (BasicAccount) o;
        return getId().equals(that.getId()) && getIban().equals(that.getIban());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getIban());
    }
}
