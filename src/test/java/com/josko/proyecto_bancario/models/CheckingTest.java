package com.josko.proyecto_bancario.models;

import com.josko.proyecto_bancario.repositories.CheckingRepository;
import com.josko.proyecto_bancario.services.InitDataSample;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CheckingTest {

    @Autowired
    private InitDataSample initData;
    @Autowired
    private CheckingRepository checkingRepository;

    @BeforeEach
    void setUp() throws Exception {
//        initData.initDataSample();
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    @DisplayName("Prueba no 1")
    public void prueba1() throws Exception {

//        initData.initDataSample();

        List<Checking> lista = checkingRepository.findAll();

        System.out.println("\nListado de las cuentas insertadas con 'InitDataSample'\n");
        System.out.println(lista);

        assertFalse(lista.isEmpty());
    }

}