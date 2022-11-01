package com.josko.proyecto_bancario.DTOs;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ThirdPartyDTO {

    @NotBlank(message = "A valid name has to be provided.")
    private String name;

    @NotBlank(message = "The username cannot be empty.")
    private String username;

    //    @JsonAlias("hashedkey")
    //    @JsonProperty(value = "hashedkey")

    @Email(message = "Email has to be valid.")
    private String email;

    @NotBlank(message = "A password must be provided.")
    private String password;

    @NotBlank(message = "A hashed key is mandatory.")
    private String hashedKey;

    public ThirdPartyDTO(String name, String username, String email, String password, String hashedKey) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.hashedKey = hashedKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }

    @Override
    public String toString() {
        return "\nThirdPartyDTO{" +
                ", name='" + name + '\'' +
                ", hashedKey='" + hashedKey + '\'' +
                '}';
    }
}
