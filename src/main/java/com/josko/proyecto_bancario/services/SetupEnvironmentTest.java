package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.models.Admin;
import com.josko.proyecto_bancario.repositories.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SetupEnvironmentTest {


    private final AdminRepository adminRepository;


    public SetupEnvironmentTest(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void SetUp () {

        Admin a1 = new Admin("alberto");

        var x = adminRepository.save(a1);
        System.out.println("\nValor de Admin: " + x);
    }

    public void tearDown() {
        System.out.println("\nEstamos en tear down!");
    }
}
