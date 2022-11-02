package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.exeptions.IdNotFoundExeption;
import com.josko.proyecto_bancario.models.AccountHolder;
import com.josko.proyecto_bancario.repositories.AccountHolderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountHolderService {

    private final AccountHolderRepository accountHolderRepository;

    /*
        Method to validate a Long ID into a main or primary AccountHolder.
    */
    public AccountHolder getAccountHolder(Long userId) {
        log.info("ACCOUNTHOLDER_SERVICE:getAccountHolder: Validating AccountHolder from ID.");

        Optional<AccountHolder> accountHolder = accountHolderRepository.findById(userId);
        if (accountHolder .isEmpty()) {
            log.warn("The account Holder user ID(" + userId.toString() + ") was not found.");
            throw new IdNotFoundExeption(userId.toString());
        }
        return accountHolder.get();
    }

    /*
    Method to validate a secondaryAccountHolder from an ID.
    */
    public Optional<AccountHolder> getSecondaryAccountHolder(Optional<Long> secondaryAccountHolder) {
        log.info("ACCOUNTHOLDER_SERVICE:getSecondaryAccountHolder: Validating a secondary AccountHolder.");

        Optional<AccountHolder> secondaryOwner = Optional.ofNullable(null);

        if (secondaryAccountHolder.isPresent()) {
            secondaryOwner = accountHolderRepository.findById(secondaryAccountHolder.get());
            if (secondaryOwner.isEmpty()) {
                log.warn("The secondary account Holder user ID is not found.");
                throw new IdNotFoundExeption(secondaryAccountHolder.get().toString());
            }
        }
        return secondaryOwner;
    }

}
