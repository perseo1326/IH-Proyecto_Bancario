package com.josko.proyecto_bancario.DTOs;


import com.josko.proyecto_bancario.models.Address;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Optional;

@Getter
@Setter
@RequiredArgsConstructor
public class AccountHolderDTO {


    @NotBlank(message = "A valid name has to be provided.")
    private String name;

    @NotBlank(message = "The username cannot be empty.")
    private String username;

    @Email(message = "Email has to be valid.")
    private String email;

    @NotBlank(message = "A password must be provided.")
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private Address mainAddress;

    private Optional<Address> secondaryAddress;



}
