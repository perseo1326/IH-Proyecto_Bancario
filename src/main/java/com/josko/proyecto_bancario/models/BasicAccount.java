package com.josko.proyecto_bancario.models;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;


@Entity
@Table(name = "basic_accounts")
@Inheritance(strategy = InheritanceType.JOINED)
public class BasicAccount {

    @Transient
    private static final BigDecimal PENALTY_FEE = new BigDecimal(40);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "basic_account_id", nullable = false)
    private Long id;

    @NotNull(message = "IBAN cannot be null.")
    @Column(unique = true)
    private String iban;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME )
    private LocalDateTime creationTime;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "currency", column = @Column(name = "currency_penalty_fee")),
            @AttributeOverride( name = "amount", column = @Column(name = "amount_penalty_fee")),
    })
    private Money penaltyFee;

    @Embedded
    protected Money balance;

    @NotNull(message = "No user was assigned to the account.")
    @OneToOne
    @JoinColumn(name = "first_account_holder_user_id")
    private AccountHolder firstAccountHolder;
    @OneToOne
    @JoinColumn(name = "second_accountholder_user_id")
    private AccountHolder secondAccountholder;

    public BasicAccount() {
    }

    public BasicAccount(AccountHolder firstAccountHolder, Optional<AccountHolder> secondAccountholder, String iban, Money balance ) {
        this.firstAccountHolder = firstAccountHolder;
        if (secondAccountholder.isPresent()) {
            this.secondAccountholder = secondAccountholder.get();
        } else {
            this.secondAccountholder = null;
        }
        this.iban = iban;
        this.balance = new Money(balance.getAmount(), balance.getCurrency());
        this.creationTime = LocalDateTime.now();
        this.penaltyFee = new Money( PENALTY_FEE );
    }


    @Override
    public String toString() {
        return "\nBasicAccount{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                ", creationTime=" + creationTime +
                ", penaltyFee=" + penaltyFee +
                ", balance=" + balance +
                ", firstAccountHolder=" + firstAccountHolder +
                ", secondAccountholder=" + secondAccountholder +
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public Money getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(Money penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
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
}
