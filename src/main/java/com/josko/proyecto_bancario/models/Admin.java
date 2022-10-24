package com.josko.proyecto_bancario.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Admins")
public class Admin extends User {

    public Admin() {
    }

    public Admin(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "\nAdmin{ " + super.toString() + " }";
    }
}
