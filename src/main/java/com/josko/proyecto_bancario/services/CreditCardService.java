package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.DTOs.CreditCardDTO;
import com.josko.proyecto_bancario.exeptions.NotValidDataException;
import com.josko.proyecto_bancario.models.AccountHolder;
import com.josko.proyecto_bancario.models.CreditCard;
import com.josko.proyecto_bancario.repositories.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditCardService {

    // CreditCard accounts have a default creditLimit of 100
    private final BigDecimal CREDIT_LIMIT = new BigDecimal(100);
    //  CreditCards have a default interestRate of 0.2
    private final BigDecimal CREDIT_CARD_INTEREST_RATE = new BigDecimal(0.2);
    private final CreditCardRepository creditCardRepository;
    private final AccountHolderService accountHolderService;

    /*
    RESTRICTIONS to validate:
        1. CreditCard accounts have a default creditLimit of 100 ( X < 100 )
        2. CreditCards may be instantiated with a creditLimit higher than 100 but not higher than 100K (100 < X 100K).
*/
    private BigDecimal validateCreditLimit(Optional<BigDecimal> creditLimit) {
        log.info("CREDITCARD_SERVICE:validateCreditLimit: Validating the Credit Card Limit.");

        BigDecimal validCreditLimit;

        // if credit limit fit -> 100 < X < 100K
        if (creditLimit.isEmpty()) {
            validCreditLimit = CREDIT_LIMIT;
        } else {
            if (creditLimit.get().compareTo(new BigDecimal(100)) < 0 || creditLimit.get().compareTo(new BigDecimal(100_000)) > 0) {
                log.warn("CREDITCARD_SERVICE:validateCreditLimit: The creditLimit value supplied is not in a valid range.");
                throw new NotValidDataException("The creditLimit value supplied is not in a valid range.");
            }
            validCreditLimit = creditLimit.get();
        }
        return validCreditLimit;
    }

    /*
        RESTRICTIONS to validate:
            3. CreditCards have a default interestRate of 0.2 (20%)
            4. CreditCards may be instantiated with an interestRate less than 0.2 but not lower than 0.1(10% < X < 20%)
     */
    private BigDecimal validateCreditCartInterestRate(Optional<BigDecimal> creditCardInterestRate) {
        log.info("CREDITCARD_SERVICE:validateCreditCartInterestRate: Validating Credit Card Interest Rate is valid.");

        BigDecimal validCreditCardInterestRate;

        if (creditCardInterestRate.isEmpty()) {
            validCreditCardInterestRate = CREDIT_CARD_INTEREST_RATE;
        }
        else {
            if (creditCardInterestRate.get().compareTo(new BigDecimal(0.1)) < 0 || creditCardInterestRate.get().compareTo(new BigDecimal(0.2)) > 0) {
                log.warn("CREDITCARD_SERVICE:validateCreditCartInterestRate: The Credit Card Interest Rate is in a not valid range.");
                throw new NotValidDataException("The Credit Card Interest Rate is in a not valid range");
            }
            validCreditCardInterestRate = creditCardInterestRate.get();
        }
        return validCreditCardInterestRate;
    }

    /*
        Method to create a new Credit Card account
     */
    public CreditCard createNewCreditCardAccount(Long userId, CreditCardDTO creditCardDTO) {
        log.info("CREDITCARD_SERVICE:createNewCreditCardAccount: Method to create a new 'CreditCard' Account.");

        Optional<AccountHolder> mainAccountHolder = accountHolderService.getMainAccountHolder(userId);

        Optional<AccountHolder> secondaryOwner = accountHolderService.getSecondaryAccountHolder(creditCardDTO.getSecondaryOwner());

        CreditCard creditCardAccount = new CreditCard(mainAccountHolder.get(), secondaryOwner, creditCardDTO.getIban(), creditCardDTO.getBalance(),
                validateCreditLimit(creditCardDTO.getCreditLimit()), validateCreditCartInterestRate(creditCardDTO.getCreditCardInterestRate()) );

        return creditCardRepository.save(creditCardAccount);
    }
}
