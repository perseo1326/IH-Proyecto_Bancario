package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.DTOs.TransferAccountHolder;
import com.josko.proyecto_bancario.exeptions.IdNotFoundExeption;
import com.josko.proyecto_bancario.exeptions.NotValidDataException;
import com.josko.proyecto_bancario.models.AccountHolder;
import com.josko.proyecto_bancario.models.BasicAccount;
import com.josko.proyecto_bancario.repositories.AccountHolderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountHolderService {

    private final ValidatorService validatorService;
    private final AccountHolderRepository accountHolderRepository;
    private final BasicAccountService basicAccountService;

    /*
        Method to find a user by his username and validate it.
     */
    public AccountHolder getSessionUser() {

        String username = validatorService.getUserAuthenticated();
        Optional<AccountHolder> user = accountHolderRepository.findByUsernameIgnoreCase(username);

        if (user.isEmpty()) {
            log.warn("The username(" + username + ") was not found.");
        }

        return user.get();
    }

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

    /*
        Welcome to the AccountHolder user showing all his owned accounts
     */
    public List<BasicAccount> accountHolderWelcome() {

        AccountHolder user = getSessionUser();

        return basicAccountService.getAllAccountsFromAccountHolderUser(user);
    }

    /*
        Show a specific account by his IBAN and owned by the current logged user
     */
    public BasicAccount getAccountByUserAndIban(String iban) {

        AccountHolder user = getSessionUser();

        // TODO: eliminar este metodo??
        return getAccountByUserAndIban(user.getName().toLowerCase(), iban);

    }

    /*
        Method that find an Account given his IBAN and a primary or secondary owner 'name'
     */
    private BasicAccount getAccountByUserAndIban(String iban, String name) {

        BasicAccount account = basicAccountService.getAccountFromIban(iban);

        // Validate that the 'name' is primary or secondary owner of the account
        if(!account.getFirstAccountHolder().getName().toLowerCase().contains(name)) {
            if (!account.getSecondAccountholder().getName().toLowerCase().contains(name)) {
                log.warn("ACCOUNTHOLDER_SERVICE:getDestinationAccountByUserAndIban: The user (" + name + ") not owned the destination account with IBAN(" + iban + ").");
                throw new NotValidDataException("The user (" + name + ") not owned the destination account with IBAN(" + iban + ").");
            }
        }

        return account;
    }

    /*
        Method for make transference between two accounts.
     */
    //    public BasicAccount setTransferToAccount(String iban, TransferAccountHolder transferAccountHolder) {
    public String validateAccountsForTranfer( String iban, TransferAccountHolder transferAccountHolder) {
        log.info("ACCOUNTHOLDER_SERVICE:setTransferToAccount: Starting a transfer process.");

        AccountHolder user = getSessionUser();

        BasicAccount baseAccount = getAccountByUserAndIban(iban, user.getName().toLowerCase());

        BasicAccount finalAccount = getAccountByUserAndIban(transferAccountHolder.getIbanDestination().toLowerCase(), transferAccountHolder.getAccountName().toLowerCase());

        return basicAccountService.validateAccountsBeforeTransfer(baseAccount, finalAccount, transferAccountHolder.getBalance());
    }




}
