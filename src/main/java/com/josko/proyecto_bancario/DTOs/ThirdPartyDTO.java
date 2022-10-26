package com.josko.proyecto_bancario.DTOs;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.josko.proyecto_bancario.enums.UserTypeEnum;

import javax.validation.constraints.NotNull;

public class ThirdPartyDTO {

    @JsonAlias("userType")
    @JsonProperty(value = "userType")
    private UserTypeEnum userTypeEnum;

    @NotNull(message = "A valid name has to be provided.")
    private String name;

    @JsonAlias("hashedkey")
    @JsonProperty(value = "hashedkey")
    private String hashedKey;

    public ThirdPartyDTO(UserTypeEnum userTypeEnum, String name, String hashedKey) {
        this.userTypeEnum = userTypeEnum;
        this.name = name;
        this.hashedKey = hashedKey;
    }

    public UserTypeEnum getUserTypeEnum() {
        return userTypeEnum;
    }

    public void setUserTypeEnum(UserTypeEnum userTypeEnum) {
        this.userTypeEnum = userTypeEnum;
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
                "userTypeEnum=" + userTypeEnum +
                ", name='" + name + '\'' +
                ", hashedKey='" + hashedKey + '\'' +
                '}';
    }
}
