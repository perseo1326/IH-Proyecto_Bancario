package com.josko.proyecto_bancario.DTOs;


import com.josko.proyecto_bancario.models.Address;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;

public class AccountHolderDTO {


    @NotNull(message = "A valid name has to be provided.")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    private Address mainAddress;

    private Optional<Address> secondaryAddress;


    public AccountHolderDTO(String name, LocalDate birthDate, Address mainAddress, Optional<Address> secondaryAddress) {
        this.name = name;
        this.birthDate = birthDate;
        this.mainAddress = mainAddress;
        this.secondaryAddress = secondaryAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(Address mainAddress) {
        this.mainAddress = mainAddress;
    }

    public Optional<Address> getSecondaryAddress() {
        return secondaryAddress;
    }

    public void setSecondaryAddress(Optional<Address> secondaryAddress) {
        this.secondaryAddress = secondaryAddress;
    }
}
