package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.DTOs.*;
import com.josko.proyecto_bancario.models.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final ThirdPartyService thirdPartyService;
    private final AccountHolderService accountHolderService;

    private final CheckingService checkingService;
    private final SavingsService savingsService;
    private final CreditCardService creditCardService;

    /*
        GET AccountHolders By ID
     */
    public List<AccountHolder> getAccountHolderById(Long userId) {

        return accountHolderService.getAccountHolderById(userId);
    }

    // GET AccountHolders By name
    public List<AccountHolder> getAccountHolderByName(String name) {

        return accountHolderService.getAccountHolderByName(name);
    }

    /*
        GET all account holders in the database.
     */
    public List<AccountHolder> findAllAccountHolders() {

        return accountHolderService.findAllAccountHolders();
    }

    /*
        GET accountHolders in a date range or since a given date, look before or after that date.
     */
    public List<AccountHolder> findByBirthDateValues(Optional<LocalDate> initialDate, Optional<LocalDate> finalDate) {

        return accountHolderService.findByBirthDateValues(initialDate, finalDate);
    }

    /*
        CREATE a new AccountHolder entity in the DB.
     */
    public AccountHolder createAccountHolderUser(@Valid AccountHolderDTO newAccountHolderDTO) {
        log.info("ADMINSERVICE:createAccountHolderUser: Creating a new AccountHolder user.");

        return accountHolderService.createNewAccountHolderUser(newAccountHolderDTO);
    }

    /*
        CREATE ThirdParty entities in the DB.
     */
    public ThirdParty createThirdPartyUser(ThirdPartyDTO newThirdPartyDTO) {
        log.info("ADMINSERVICE:createThirdPartyUser: Creating a new ThirdParty user.");

        return thirdPartyService.createNewThirdPartyUser(newThirdPartyDTO);
    }

    /*
        Method for create new Checking accoungs related to a user.
     */
    public Checking createNewCheckingAccount(Long userId, @Valid CheckingDTO checkingDTO) {

        return checkingService.createNewCheckingAccount(userId, checkingDTO);
    }

    /*
    CREATE a new 'Savings' account for a selected AccountHolder.
 */
    public Savings createNewSavingsAccount(Long userId, SavingsDTO newSavingsDTO) {

        return savingsService.createNewSavingsAccount( userId, newSavingsDTO);
    }

    /*
        Method for create a new 'CreditCard' associated to a user.
    */

    public CreditCard createNewCreditCardAccount(Long userId, CreditCardDTO creditCardDTO) {

        return creditCardService.createNewCreditCardAccount(userId, creditCardDTO);
    }

    /*
        Method to show the accounts that belong to an AccountHolder
     */
    public List<BasicAccount>  getAllAccountsFromAccountHolderUser(Long userId) {

        return accountHolderService.getAllAccountsFromAccountHolderUser(userId);
    }

    /*
        Method for search a specific account based on his IBAN
     */
    public Optional<BasicAccount> getAccountFromAccountHolderByIban(String iban) {

        return accountHolderService.getAccountFromAccountHolderByIban(iban);
    }
}
