package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.exeptions.IdNotFoundExeption;
import com.josko.proyecto_bancario.models.*;
import com.josko.proyecto_bancario.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class InitDataSample implements ApplicationRunner {

    private final CheckingRepository checkingRepository;
    private final SavingsRepository savingsRepository;
    private final CreditCardRepository creditCardRepository;
    private final AccountHolderRepository accountHolderRepository;

    // Default values for the entities
    private final BigDecimal MINIMUM_BALANCE = new BigDecimal(250);
    private final BigDecimal MONTLY_MAINTENANCE_FEE = new BigDecimal(12);
    private final BigDecimal SAVINGS_INTEREST_RATE = new BigDecimal(0.0025);
    private final BigDecimal MINIMUM_BALANCE_SAVINGS = new BigDecimal(1000);
    private final BigDecimal CREDIT_LIMIT = new BigDecimal(100);
    private final BigDecimal CREDIT_CARD_INTEREST_RATE = new BigDecimal(0.2);


    public void initDataSample() {
        log.info("CONFIGURATION:INIT_DATA_SAMPLE:run: Inicialización de valores en la base de datos.");

        Checking ch1 = new Checking(getUser("alberto"), Optional.ofNullable(getUser("ivan")), "CH020", new Money(new BigDecimal(335)), MINIMUM_BALANCE, MONTLY_MAINTENANCE_FEE );
        Checking ch2 = new Checking(getUser("ernesto"), Optional.ofNullable(getUser(null)), "CH021", new Money(new BigDecimal(123)), MINIMUM_BALANCE, MONTLY_MAINTENANCE_FEE);
        Checking ch3 = new Checking(getUser("ernesto"), Optional.ofNullable(getUser(null)), "CH022", new Money(new BigDecimal(453.23)), MINIMUM_BALANCE, MONTLY_MAINTENANCE_FEE);
        Checking ch4 = new Checking(getUser("kevin"), Optional.ofNullable(getUser(null)), "CH023", new Money(new BigDecimal(814.20)), MINIMUM_BALANCE, MONTLY_MAINTENANCE_FEE);

        checkingRepository.saveAll(Arrays.asList(ch1, ch2, ch3, ch4));

        Savings s1 = new Savings(getUser("alberto"), Optional.ofNullable(getUser("daniel")), "SA040", new Money(new BigDecimal(159.98)), SAVINGS_INTEREST_RATE, MINIMUM_BALANCE_SAVINGS );
        Savings s2 = new Savings(getUser("ernesto"), Optional.ofNullable(getUser(null)), "SA041", new Money(new BigDecimal(753)), SAVINGS_INTEREST_RATE, MINIMUM_BALANCE_SAVINGS );
        Savings s3 = new Savings(getUser("ivan"), Optional.ofNullable(getUser(null)), "SA042", new Money(new BigDecimal(546.25)), SAVINGS_INTEREST_RATE, MINIMUM_BALANCE_SAVINGS );
        Savings s4 = new Savings(getUser("kevin"), Optional.ofNullable(getUser(null)), "SA043", new Money(new BigDecimal(74)), SAVINGS_INTEREST_RATE, MINIMUM_BALANCE_SAVINGS );

        savingsRepository.saveAll(Arrays.asList(s1, s2, s3, s4));

        CreditCard cd1 = new CreditCard(getUser("hernan"), Optional.ofNullable(getUser("alberto")), "CD080", new Money(new BigDecimal(963.54)), CREDIT_LIMIT, CREDIT_CARD_INTEREST_RATE );
        CreditCard cd2 = new CreditCard(getUser("ivan"), Optional.ofNullable(getUser("ernesto")), "CD081", new Money(new BigDecimal(651.20)), CREDIT_LIMIT, CREDIT_CARD_INTEREST_RATE );
//        CreditCard cd3 = new CreditCard(getUser(), Optional.ofNullable(getUser()), "", new Money(new BigDecimal()), CREDIT_LIMIT, CREDIT_CARD_INTEREST_RATE );

        creditCardRepository.saveAll(Arrays.asList(cd1, cd2));

        System.out.println("\nEstamos en nuestro nuevo servicio de inicialización.\n");

    }

    private AccountHolder getUser(String name) {

        if (name != null) {
            List<AccountHolder> usuarios = accountHolderRepository.findByNameContainsOrderByName(name);
            if (usuarios.isEmpty())
                throw new IdNotFoundExeption("No se encontró el usuario con nombre: " + name);
            return usuarios.get(0);
        }
        return null;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initDataSample();
    }
}
