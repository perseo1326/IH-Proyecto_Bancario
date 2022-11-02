package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.DTOs.CheckingDTO;
import com.josko.proyecto_bancario.models.AccountHolder;
import com.josko.proyecto_bancario.models.Checking;
import com.josko.proyecto_bancario.repositories.CheckingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CheckingService {

    private final AccountHolderService accountHolderService;
    private final CheckingRepository checkingRepository;

    private final BigDecimal MINIMUM_BALANCE = new BigDecimal(250);
    private final BigDecimal MONTLY_MAINTENANCE_FEE = new BigDecimal(12);


    /*
        POST: Creacion de una nueva cuenta del tipo 'Checking' para el usuario seleccionado
    */
    public Checking createNewCheckingAccount(Long userId, CheckingDTO checkingDTO) {
        log.info("CHECKING_ACCOUNT_SERVICE:createNewCheckingAccount: Creating a new 'Checking' for user ID: [" + userId.toString() + "]");

        AccountHolder mainAccountHolder = accountHolderService.getAccountHolder(userId);

        Optional<AccountHolder> secondaryOwner = accountHolderService.getSecondaryAccountHolder(checkingDTO.getSecondaryOwner());

        Checking checkingAccount = new Checking(mainAccountHolder, secondaryOwner, checkingDTO.getIban(), checkingDTO.getBalance(), MINIMUM_BALANCE, MONTLY_MAINTENANCE_FEE);

        return checkingRepository.save(checkingAccount);
    }

}
