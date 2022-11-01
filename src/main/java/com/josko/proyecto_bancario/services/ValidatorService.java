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
            if (RoleEnum.ROL_USER.contains(role.toUpperCase())) {
                roleSet.add( roleRepository.findByRoleName(RoleEnum.ROL_THIRDPARTY.getValue(role.toUpperCase())));
            }
        }

        // No Role was specified, we assign the default: THIRDPARTY
        if (roleSet.isEmpty()){
            roleSet.add( roleRepository.findByRoleName(RoleEnum.ROL_THIRDPARTY));
        }
        return roleSet;
    }



}
