package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.DTOs.*;
import com.josko.proyecto_bancario.enums.RoleEnum;
import com.josko.proyecto_bancario.exeptions.NotValidDataException;
import com.josko.proyecto_bancario.models.*;
import com.josko.proyecto_bancario.repositories.AccountHolderRepository;
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

    private final AddressService addressService;
    private final UserService userService;
    private final ThirdPartyService thirdPartyService;
    private final AccountHolderService accountHolderService;
    private final AccountHolderRepository accountHolderRepository;
    private final BasicAccountService basicAccountService;
    private final CheckingService checkingService;
    private final SavingsService savingsService;
    private final CreditCardService creditCardService;

    /*
        - Obtener un AccountHolder por su ID.
     */
    public List<AccountHolder> getAccountHolderById(Long userId) {

        AccountHolder user = accountHolderService.getAccountHolder(userId);

        List<AccountHolder> users = new ArrayList<>();
        users.add(user);
        return users;
    }

    /*
        - Obtener el listado de AccountHolders por nombre.
    */
    public List<AccountHolder> getAccountHolderByName(String name) {

        List<AccountHolder> users = accountHolderRepository.findByNameContainsOrderByName(name);
        if (users.isEmpty()) {
            log.warn("ADMIN_SERVICE:getAccountHolderByName: The given name(" + name + ") was not found.");
            throw new NotValidDataException("The search with the name '" + name + "' did not give any results.");
        }
        return users;
    }

    /*
        GET all account holders in the database.
     */
    public List<AccountHolder> findAllAccountHolders() {

        return accountHolderRepository.findAll();
    }

    /*
        GET accountHolders in a date range or since a given date, look before or after that date.
     */
    public List<AccountHolder> findByBirthDateValues(Optional<LocalDate> initialDate, Optional<LocalDate> finalDate) {

        // TODO: no se puede usar sentencias ELSE_IF debido a que el metodo DEBE contener un return al final.
        if (initialDate.isEmpty() && finalDate.isEmpty() ) {
            log.warn("ADMIN_SERVICE:findByBirthDateValues: No Date values provided for search.");
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

        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setName(newAccountHolderDTO.getName());
        accountHolder.setUsername(newAccountHolderDTO.getUsername());
        accountHolder.setEmail(newAccountHolderDTO.getEmail());
        accountHolder.setPassword(newAccountHolderDTO.getPassword());
        accountHolder.setBirthDate(newAccountHolderDTO.getBirthDate());

        Address mainAddress = addressService.saveNewAddress(newAccountHolderDTO.getMainAddress());
        accountHolder.setMainAddress(mainAddress);

        if (newAccountHolderDTO.getSecondaryAddress().isPresent()) {

            Address secondaryAddress = addressService.saveNewAddress(newAccountHolderDTO.getSecondaryAddress().get());
            accountHolder.setSecondaryAddress(secondaryAddress);
        }

        accountHolder = (AccountHolder) userService.createNewUser(accountHolder, RoleEnum.ROL_USER);
        return accountHolderRepository.save(accountHolder);

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

        return basicAccountService.getAllAccountsFromAccountHolderUser(userId);
    }

    /*
        Method for search a specific account based on his IBAN
     */
    public BasicAccount getAccountFromIban(String iban) {

        return basicAccountService.getAccountFromIban(iban);
    }

    /*
        Method for change the balance of an account, selected by his IBAN and confirmed by his owner
     */
    public BasicAccount changeBalanceBasicInAccount(Long userId, ChangeBalance changeBalance) {
        log.info("BASICACCOUNT_SERVICE:changeBalanceBasicInAccount: 'BasicAccount' that will be modified in his balance. user ID(" + userId.toString() + "), IBAN(" + changeBalance.getIban() + ").");

        BasicAccount account = basicAccountService.getAccountFromIban(changeBalance.getIban());

        if(account.getFirstAccountHolder().getId().equals(userId) || account.getSecondAccountholder().getId().equals(userId)) {

            account.setBalance(changeBalance.getBalance());
        } else {
            log.warn("The AccountHolder with ID(" + userId.toString() + " is not the owner of the account with IBAN(" + changeBalance.getIban() + ").");
            throw new NotValidDataException("The AccountHolder with ID(" + userId.toString() + ") is not the owner of the account with IBAN(" + changeBalance.getIban() + ").");
        }

        return basicAccountService.save(account);
    }
}
