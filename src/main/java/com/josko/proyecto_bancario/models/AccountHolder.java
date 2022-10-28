package com.josko.proyecto_bancario.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "AccountHolders")
public class AccountHolder extends User {


    @NotNull (message = "The birthdate is mandatory")
    @Column(nullable = false)
    private LocalDate birthDate;

    @OneToOne
    @NotNull (message = "At least one address must given.")
    @JoinColumn(name = "address_address_id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "mailing_address_address_id")
    private Address secondaryAddress;

    public AccountHolder() {
    }

    public AccountHolder(String name, LocalDate birthDate, Address address, Address secondaryAddress) {
        super(name);
        this.birthDate = birthDate;
        this.address = address;
        this.secondaryAddress = secondaryAddress;
    }

    @Override
    public String toString() {
        return "\nAccountHolder{" + super.toString() +
                "birthDate=" + birthDate +
                ", address=" + address +
                ", mailingAddress=" + secondaryAddress +
                '}';
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setSecondaryAddress(Address mailingAddress) {
        this.secondaryAddress = mailingAddress;
    }

    public Address getSecondaryAddress() {
        return secondaryAddress;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


}
