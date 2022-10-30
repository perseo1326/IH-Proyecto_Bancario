package com.josko.proyecto_bancario.models;

import com.josko.proyecto_bancario.repositories.RoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleTest {

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Prueba de repositorio")
    public void getAllRoles() {

        List<Role> lista = roleRepository.findAll();

        System.out.println("\nListado de todos los datos en la tabla 'Role'" + lista.toString());

        assertFalse(lista.isEmpty());
    }
}