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
    @JoinColumn(name = "main_address_address_id")
    private Address mainAddress;

    @OneToOne
    @JoinColumn(name = "secondary_address_address_id")
    private Address secondaryAddress;

    public AccountHolder() {
    }

    public AccountHolder(String name, String username, String email, String password, LocalDate birthDate, Address mainAddress, Address secondaryAddress) {
        super(name, username, email, password);
        this.birthDate = birthDate;
        this.mainAddress = mainAddress;
        this.secondaryAddress = secondaryAddress;
    }

    @Override
    public String toString() {
        return "\nAccountHolder{" + super.toString() +
                "birthDate=" + birthDate +
                ", address=" + mainAddress +
                ", mailing Address=" + secondaryAddress +
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

    public Address getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(Address address) {
        this.mainAddress = address;
    }


}
