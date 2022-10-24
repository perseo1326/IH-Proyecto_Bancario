package com.josko.proyecto_bancario.models;

import com.josko.proyecto_bancario.repositories.AdminRepository;
import com.josko.proyecto_bancario.services.SetupEnvironmentTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AdminTest {

    private final SetupEnvironmentTest setupEnvironmentTest;
    private final AdminRepository adminRepository;

    @Autowired
    public AdminTest(SetupEnvironmentTest setupEnvironmentTest, AdminRepository adminRepository) {
        this.setupEnvironmentTest = setupEnvironmentTest;
        this.adminRepository = adminRepository;
    }


    @BeforeEach
    void setUp() {
        setupEnvironmentTest.SetUp();
    }

    @AfterEach
    void tearDown() {
        setupEnvironmentTest.tearDown();
    }

    @Test
    @DisplayName("Primer Test para admin")
    public void initTest() {

        List<Admin> lista =  adminRepository.findAll();
        System.out.println("\nMostrando todos los administradores:\n" + lista.toString());

    }
}