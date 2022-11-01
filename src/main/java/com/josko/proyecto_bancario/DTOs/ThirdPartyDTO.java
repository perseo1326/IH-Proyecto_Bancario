package com.josko.proyecto_bancario.DTOs;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class ThirdPartyDTO {

    @NotNull(message = "A valid name has to be provided.")
    private String name;

    @JsonAlias("hashedkey")
    @JsonProperty(value = "hashedkey")
    private String hashedKey;

    public ThirdPartyDTO(String name, String hashedKey) {
        this.name = name;
        this.hashedKey = hashedKey;
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
