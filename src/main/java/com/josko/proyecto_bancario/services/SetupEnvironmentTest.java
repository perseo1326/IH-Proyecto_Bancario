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


    }

    public void tearDown() { }
}
