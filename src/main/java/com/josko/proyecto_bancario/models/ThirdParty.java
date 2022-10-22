package com.josko.proyecto_bancario.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "ThirdParties")
public class ThirdParty extends User {

    private String hashedKey;

    public ThirdParty() {
    }

    public ThirdParty(String name, String hashedKey) {
        super(name);
        this.hashedKey = hashedKey;
    }

    @Override
    public String toString() {
        return "\nThirdParty{" + super.toString() +
                "hashedKey='" + hashedKey + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ThirdParty that = (ThirdParty) o;
        return getHashedKey().equals(that.getHashedKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getHashedKey());
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}
