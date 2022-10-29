package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.exeptions.IdNotFoundExeption;
import com.josko.proyecto_bancario.models.AccountHolder;
import com.josko.proyecto_bancario.repositories.AccountHolderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidatorService {

    // default interes rate = 0.25% = 0.0025
    private final BigDecimal INTERES_RATE = new BigDecimal(0.0025);
    // default minimumBalance = 1K
    private final BigDecimal MINIMUM_BALANCE = new BigDecimal(1000);

    private final AccountHolderRepository accountHolderRepository;


    public boolean isSecretKeyValid(String secretKey) {

        if (!secretKey.matches("[0-9]{4}"))
            throw new IllegalArgumentException("Not a valid hashed key supplied.");
        return true;
    }

    /*
        Method to validate a Long ID into an main or primary AccountHolder.
     */
    public Optional<AccountHolder> getMainAccountHolder(Long userId) {
        log.info("VALIDATORSERVICE:getMainAccountHolder: Validating AccountHolder from ID.");

        Optional<AccountHolder> accountHolder = accountHolderRepository.findById(userId);
        if (accountHolder .isEmpty()) {
            log.warn("The account Holder user ID is not found.");
            throw new IdNotFoundExeption(userId.toString());
        }
        return accountHolder;
    }

    /*
        Method to validate a secondaryAccountHolder from an ID.
     */
    public Optional<AccountHolder> getSecondaryAccountHolder(Optional<Long> secondaryAccountHolder) {
        log.info("ADMINSERVICE:getSecondaryAccountHolder: Validating a secondary AccountHolder.");

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


    /*
        Method for validate an 'InteresRate' value is in a valid range.
     */
    public BigDecimal validateInteresRate(Optional<BigDecimal> interesRate) {
        log.info("VALIDATORSERVICE:validateInteresRate: Validating the interes rate given.");

        BigDecimal validInteresRate;

        if (interesRate.isEmpty()) {
            validInteresRate = INTERES_RATE;
        } else {
            /*
                RESTRICTION:
                    - Savings accounts have a default interest rate of 0.0025 ( 0.25%)
                    - Savings accounts may be instantiated with an interest rate other than the default, with a maximum interest rate of 0.5 (0.25% < X < 0.5%)
             */
            if ((interesRate.get().compareTo(new BigDecimal(0.0025)) < 0) || interesRate.get().compareTo(new BigDecimal(0.005)) > 0) {
                log.error("VALIDATORSERVICE:validateInteresRate: The interes rate is not in a valid range.");
                throw new IllegalArgumentException("The interes rate do not meet the request.");
            }
            validInteresRate = interesRate.get();
        }
        return validInteresRate;
    }


    /*
        Method for validate the 'minimumBalance' against a range.
     */
    public BigDecimal validateMinimumBalance(Optional<BigDecimal> minimumBalance) {
        log.info("VALIDATORSERVICE:validateMinimumBalance: Validating the minimum balance supplied.");

        BigDecimal validMinimumBalance;
        if (minimumBalance.isEmpty()) {
            validMinimumBalance = MINIMUM_BALANCE;
        } else {
            /*
                RESTRICTION:
                    - Savings accounts should have a default minimumBalance of 1000 (X > 1K)
                    - Savings accounts may be instantiated with a minimum balance of less than 1000 but no lower than 100 ( 100 < X < 1K)
             */
            if (minimumBalance.get().compareTo(new BigDecimal(100)) < 0 || minimumBalance.get().compareTo(new BigDecimal(1000)) > 0) {
                log.error("PERSISTENCE:SAVINGS:Constructor: The minimumBalance is not valid (" + minimumBalance.toString() + ").");
                throw new IllegalArgumentException("The minimumBalance is not in a valid range.");
            }
            validMinimumBalance = minimumBalance.get();
        }
        return validMinimumBalance;
    }





}
