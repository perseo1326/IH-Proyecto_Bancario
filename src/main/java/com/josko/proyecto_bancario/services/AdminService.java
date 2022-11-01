package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.DTOs.*;
import com.josko.proyecto_bancario.exeptions.IdNotFoundExeption;
import com.josko.proyecto_bancario.exeptions.IdNotValidExeption;
import com.josko.proyecto_bancario.exeptions.NotValidDataException;
import com.josko.proyecto_bancario.models.*;
import com.josko.proyecto_bancario.repositories.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final ThirdPartyService thirdPartyService;
    private final AccountHolderService accountHolderService;
    private final AccountHolderRepository accountHolderRepository;  // *
    private final SavingsRepository savingsRepository;  // *
    private final CheckingRepository checkingRepository;  // *
    private final CreditCardRepository creditCardRepository;  // *
    private final ValidatorService validatorService;  // *

    /*
        GET AccountHolders By ID
     */
    public List<AccountHolder> getAccountHolderById(Long userId) {

        return accountHolderService.getAccountHolderById(userId);
    }

    // GET AccountHolders By name
    public List<AccountHolder> getAccountHolderByName(String name) {

        List<AccountHolder> users = accountHolderRepository.findByNameContainsOrderByName(name);
        if (users.isEmpty()) {
            log.warn("ADMINSERVICE:getAccountHolderByName: The given name(" + name + ") was not found.");
            throw new IdNotFoundExeption(name);
        }
        return users;
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

        // TODO: no se puede usar sentencias ELSE_IF debido a que el metodo DEBE contener un return al final.
        if (initialDate.isEmpty() && finalDate.isEmpty() ) {
            log.warn("ADMINSERVICE:findByBirthDateValues: No Date values provided for search.");
            throw new NotValidDataException("No Date values provided for search.");
        }

        if (initialDate.isPresent() && finalDate.isEmpty()) {
            return accountHolderRepository.findByBirthDateLessThanEqualOrderByNameAsc(initialDate.get());
        }

        if (initialDate.isEmpty() && finalDate.isPresent() )  {
            return accountHolderRepository.findByBirthDateGreaterThanEqualOrderByNameAsc(finalDate.get());
        }

        // initialDate.isPresent() && finalDate.isPresent()
        return accountHolderRepository.findByBirthDateBetween(initialDate.get(), finalDate.get());
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
        CREATE a new 'Savings' account for a selected AccountHolder.
     */
    public Savings createNewSavingsAccount(Long userId, SavingsDTO newSavingsDTO) {
        log.info("ADMINSERVICE:createNewSavingsAccount: Creating a new 'Savings' for user ID: [" + userId.toString() + "]");

        Optional<AccountHolder> mainAccountHolder = validatorService.getMainAccountHolder(userId);

        Optional<AccountHolder> secondaryOwner = validatorService.getSecondaryAccountHolder(newSavingsDTO.getSecondaryOwner());

        Savings savingsAccount = new Savings(mainAccountHolder.get(), secondaryOwner, newSavingsDTO.getIban(), newSavingsDTO.getBalance(), newSavingsDTO.getInteresRate(), newSavingsDTO.getMinimumBalance() );

        return savingsRepository.save(savingsAccount);
    }

    /*
        Method for create new Checking accoungs related to a user.
     */
    public Checking createNewCheckingAccount(Long userId, @Valid CheckingDTO checkingDTO) {
        log.info("ADMINSERVICE:createNewCheckingAccount: Creating a new 'Checking' for user ID: [" + userId.toString() + "]");

        Optional<AccountHolder> mainAccountHolder = validatorService.getMainAccountHolder(userId);

        Optional<AccountHolder> secondaryOwner = validatorService.getSecondaryAccountHolder(checkingDTO.getSecondaryOwner());

        Checking checkingAccount = new Checking(mainAccountHolder.get(), secondaryOwner, checkingDTO.getIban(), checkingDTO.getBalance());

        return checkingRepository.save(checkingAccount);
    }

    /*
        Method for create a new 'CreditCard' associated to a user.
    */
    public CreditCard createNewCreditCardAccount(Long userId, CreditCardDTO creditCardDTO) {
        log.info("ADMINSERVICE:createNewCreditCardAccount: Method to create a new 'CreditCard' Account.");

        Optional<AccountHolder> mainAccountHolder = validatorService.getMainAccountHolder(userId);

        Optional<AccountHolder> secondaryOwner = validatorService.getSecondaryAccountHolder(creditCardDTO.getSecondaryOwner());

        CreditCard creditCardAccount = new CreditCard(mainAccountHolder.get(), secondaryOwner, creditCardDTO.getIban(), creditCardDTO.getBalance(), creditCardDTO.getCreditLimit(), creditCardDTO.getCreditCardInteresRate());

        return creditCardRepository.save(creditCardAccount);
    }
}
