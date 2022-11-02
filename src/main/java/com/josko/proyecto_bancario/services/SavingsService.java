package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.DTOs.SavingsDTO;
import com.josko.proyecto_bancario.exeptions.NotValidDataException;
import com.josko.proyecto_bancario.models.AccountHolder;
import com.josko.proyecto_bancario.models.Savings;
import com.josko.proyecto_bancario.repositories.SavingsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SavingsService {

    // default interes rate = 0.25% = 0.0025
    private final BigDecimal SAVINGS_INTEREST_RATE = new BigDecimal(0.0025);
    // default minimumBalance = 1K
    private final BigDecimal MINIMUM_BALANCE = new BigDecimal(1000);

    private final AccountHolderService accountHolderService;
    private final SavingsRepository savingsRepository;

    /*
        Method for validate an 'InterestRate' value is in a valid range.
        RESTRICTION:
            - Savings accounts have a default interest rate of 0.0025 ( 0.25%)
            - Savings accounts may be instantiated with an interest rate other than the default, with a maximum interest rate of 0.5 (0.25% < X < 0.5%)
    */
    private BigDecimal validateInterestRate(Optional<BigDecimal> interesRate) {
        log.info("SAVINGS_SERVICE:validateInteresRate: Validating the interes rate given.");

        BigDecimal validInteresRate;

        if (interesRate.isEmpty()) {
            validInteresRate = SAVINGS_INTEREST_RATE;
        } else {
            if ((interesRate.get().compareTo(new BigDecimal(0.0025)) < 0) || interesRate.get().compareTo(new BigDecimal(0.005)) > 0) {
                log.error("SAVINGS_SERVICE:validateInteresRate: The interest rate (" + interesRate.get().toString() + ") is not in a permitted range.");
                throw new NotValidDataException("The interest rate (" + interesRate.get().toString() + ") is not in a permitted range.");
            }
            validInteresRate = interesRate.get();
        }
        return validInteresRate;
    }

    /*
        Method for validate the 'minimumBalance' against a range.
        RESTRICTION:
            - Savings accounts should have a default minimumBalance of 1000 (X > 1K)
            - Savings accounts may be instantiated with a minimum balance of less than 1000 but no lower than 100 ( 100 < X < 1K)
    */
    private BigDecimal validateMinimumBalance(Optional<BigDecimal> minimumBalance) {
        log.info("CHECKING_SERVICE:validateMinimumBalance: Validating the minimum balance.");

        BigDecimal validMinimumBalance;

        if (minimumBalance.isEmpty()) {
            validMinimumBalance = MINIMUM_BALANCE;
        } else {
            if (minimumBalance.get().compareTo(new BigDecimal(100)) < 0 || minimumBalance.get().compareTo(new BigDecimal(1000)) > 0) {
                log.warn("CHECKING_SERVICE:validateMinimumBalance: The minimumBalance (" + minimumBalance.toString() + ") is out the range. ");
                throw new NotValidDataException("The minimumBalance ('" + minimumBalance.get().toString() + ") is not in a valid range.");
            }
            validMinimumBalance = minimumBalance.get();
        }
        return validMinimumBalance;
    }

    /*
        POST: creacion de una cuenta de tipo 'Savings' para el usuario seleccionando
     */
    public Savings createNewSavingsAccount(Long userId, SavingsDTO savingsDTO) {
        log.info("SAVINGS_SERVICE:createNewSavingsAccount: Creating a new 'Savings' for user ID: [" + userId.toString() + "]");

        Optional<AccountHolder> mainAccountHolder = accountHolderService.getMainAccountHolder(userId);

        Optional<AccountHolder> secondaryOwner = accountHolderService.getSecondaryAccountHolder(savingsDTO.getSecondaryOwner());

        Savings savingsAccount = new Savings(mainAccountHolder.get(), secondaryOwner, savingsDTO.getIban(), savingsDTO.getBalance(), validateInterestRate(savingsDTO.getInteresRate()), validateMinimumBalance(savingsDTO.getMinimumBalance()));

        return savingsRepository.save(savingsAccount);
    }


}
