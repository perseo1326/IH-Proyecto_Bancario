package com.josko.proyecto_bancario.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "AccountHolders")
public class AccountHolder extends User {


    private LocalDate birthDate;

    @OneToOne
    @JoinColumn(name = "address_address_id")
    private Address address;

    @OneToOne
    @JoinColumn(name = "mailing_address_address_id")
    private Address mailingAddress;

    public AccountHolder() {
    }

    public AccountHolder(String name, LocalDate birthDate, Address address, Address mailingAddress) {
        super(name);
        this.birthDate = birthDate;
        this.address = address;
        this.mailingAddress = mailingAddress;
    }

    @Override
    public String toString() {
        return "\nAccountHolder{" + super.toString() +
                "birthDate=" + birthDate +
                ", address=" + address +
                ", mailingAddress=" + mailingAddress +
                '}';
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


}
