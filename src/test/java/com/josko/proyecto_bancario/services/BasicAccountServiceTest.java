package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.models.Money;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BasicAccountServiceTest {

    @Autowired
    private BasicAccountService basicAccountService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Comprobacion de IGUAL tipo de currency  ")
    void validateSameCurrencyTest() {

        Money a = new Money(new BigDecimal(50), Currency.getInstance("EUR"));
        Money b = new Money(new BigDecimal(20), Currency.getInstance("EUR"));

        System.out.println("\nValidacion de comprobacion de tipo de currency");
        System.out.println("\n objeto a =" + a.toString());
        System.out.println("\n objeto b =" + b.toString());

        assertTrue( basicAccountService.validateSameCurrency(a, b));
    }

    @Test
    @DisplayName("Comprobacion de DIFERENTE tipo de currency  ")
    void validateSameCurrencyTestDifferent() {

        Money a = new Money(new BigDecimal(50), Currency.getInstance("GBP"));
        Money b = new Money(new BigDecimal(20), Currency.getInstance("EUR"));

        System.out.println("\nValidacion de comprobacion de tipo de currency");
        System.out.println("\n objeto a =" + a.toString());
        System.out.println("\n objeto b =" + b.toString());

        assertFalse( basicAccountService.validateSameCurrency(a, b));
    }
}