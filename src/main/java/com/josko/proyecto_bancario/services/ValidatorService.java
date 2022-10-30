package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.enums.RoleEnum;
import com.josko.proyecto_bancario.exeptions.IdNotFoundExeption;
import com.josko.proyecto_bancario.models.AccountHolder;
import com.josko.proyecto_bancario.models.Role;
import com.josko.proyecto_bancario.repositories.AccountHolderRepository;
import com.josko.proyecto_bancario.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidatorService {

    // default interes rate = 0.25% = 0.0025
    private final BigDecimal SAVINGS_INTERES_RATE = new BigDecimal(0.0025);
    // default minimumBalance = 1K
    private final BigDecimal MINIMUM_BALANCE = new BigDecimal(1000);
    // CreditCard accounts have a default creditLimit of 100
    private final BigDecimal CREDIT_LIMIT = new BigDecimal(100);
    //  CreditCards have a default interestRate of 0.2
    private final BigDecimal CREDIT_CARD_INTERES_RATE = new BigDecimal(0.2);
    private final RoleRepository roleRepository;
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
            validInteresRate = SAVINGS_INTERES_RATE;
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

    /*
        RESTRICTIONS to validate:
            1. CreditCard accounts have a default creditLimit of 100 ( X < 100 )
            2. CreditCards may be instantiated with a creditLimit higher than 100 but not higher than 100K (100 < X 100K).
    */
    public BigDecimal validateCreditLimit(Optional<BigDecimal> creditLimit) {
        log.info("VALIDATORSERVICE:validateCreditLimit: Validating the Credit Limit.");

        BigDecimal validCreditLimit;

        // if credit limit fit -> 100 < X < 100K

        if (creditLimit.isEmpty()) {
            validCreditLimit = CREDIT_LIMIT;
        } else {
            if (creditLimit.get().compareTo(new BigDecimal(100)) < 0 || creditLimit.get().compareTo(new BigDecimal(100_000)) > 0) {
                log.warn("VALIDATORSERVICE:validateCreditLimit: the creditLimit value supplied is not in a valid range.");
                throw new IllegalArgumentException("The credit limit is not valid.");
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

    public BigDecimal validateCreditCartInteresRate(Optional<BigDecimal> creditCardInteresRate) {
        log.info("VALIDATORSERVICE:validateCreditCartInteresRate: Validating Credit Card Interes Rate is valid.");

        BigDecimal validCreditCartInteresRate;

        if (creditCardInteresRate.isEmpty()) {
            validCreditCartInteresRate = CREDIT_CARD_INTERES_RATE;
        }
        else {
            if (creditCardInteresRate.get().compareTo(new BigDecimal(0.1)) < 0 || creditCardInteresRate.get().compareTo(new BigDecimal(0.2)) > 0) {
                log.warn("VALIDATORSERVICE:validateCreditCartInteresRate: The Credit Card Interes Rate is in a not valid range.");
                throw new IllegalArgumentException("The interes rate is not in a valid range.");
            }
            validCreditCartInteresRate = creditCardInteresRate.get();
        }
        return validCreditCartInteresRate;
    }

    public Set<Role> validateRoleStringSet(Set<String> roleStringSet) {

        Set<Role> roleSet = new HashSet<>();

        for ( String role : roleStringSet) {
            if (RoleEnum.ROLE_USER.contains(role.toUpperCase())) {
                roleSet.add( roleRepository.findByRoleName(RoleEnum.ROLE_THIRDPARTY.getValue(role.toUpperCase())));
            }
        }

        // No Role was specified, we assign the default: THIRDPARTY
        if (roleSet.isEmpty()){
            roleSet.add( roleRepository.findByRoleName(RoleEnum.ROLE_THIRDPARTY));
        }
        return roleSet;
    }



}
