package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.exeptions.NotValidDataException;
import com.josko.proyecto_bancario.exeptions.SearchWithNoResultsException;
import com.josko.proyecto_bancario.models.AccountHolder;
import com.josko.proyecto_bancario.models.BasicAccount;
import com.josko.proyecto_bancario.models.Money;
import com.josko.proyecto_bancario.repositories.BasicAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BasicAccountService {

    private final String FINALIZATION_MESSAGE = "Transaction successfully!";

    private final BasicAccountRepository basicAccountRepository;


    /*
        Method for search a specific account based on his IBAN
    */
    public BasicAccount getAccountFromIban(String iban) {

        Optional<BasicAccount> basicAccount = basicAccountRepository.findByIbanIgnoreCase(iban);
        if (basicAccount.isEmpty()) {
            throw new NotValidDataException("The given IBAN(" + iban + ") was not found.");
        }

        return basicAccount.get();
    }

    /*
        Method to show the accounts that belong to an AccountHolder
     */
    public List<BasicAccount> getAllAccountsFromAccountHolderUser(AccountHolder accountHolder) {
        log.info("BASICACCOUNT_SERVICE:getAllAccountsFromAccountHolderUser: Getting all accounts from user ID(" + accountHolder.getId().toString() + ").");

        List<BasicAccount> basicAccountList = basicAccountRepository.findByFirstOrSecondAccountHolderById_OrderedByName(accountHolder.getId(), accountHolder.getId());

        if (basicAccountList.isEmpty()) {
            throw new SearchWithNoResultsException("The user ID(" + accountHolder.getId().toString() + ") does not have any account.");
        }
        return basicAccountList;
    }

    /*
        Method for change the balance of an account, selected by his IBAN and confirmed by his owner
     */
    public BasicAccount save(BasicAccount basicAccount) {

        return basicAccountRepository.save(basicAccount);
    }

    /*
        Method to validate that two 'Money' objects works with the same 'currency'
     */
    public boolean validateSameCurrency(Money moneyA, Money moneyB) {

        if (moneyA.getCurrency().equals(moneyB.getCurrency())) {
            return true;
        }
        return false;
    }

    /*
        Method to validate enough funds to process the transaction.
     */
    public boolean validateFunds(Money base, Money amount) {

        // make a copy to avoid unwanted modifications.
        Money value = new Money(base.getAmount(), base.getCurrency());

        var x = value.decreaseAmount(amount).compareTo(new BigDecimal(0));
        if(x < 0) {

            return false; // invalid case, not enough funds.
        }

        return true;    // valid case, ALL good, proceed!!
    }

    /*
        Method to perform a transfer between two verified accounts
     */
    public String validateAccountsBeforeTransfer(BasicAccount baseAccount, BasicAccount finalAccount, Money amount) {
        log.info("BASICACCOUNT_SERVICE:validateAccountsBeforeTransfer: Process transfer from IBAN(" + baseAccount.getIban() + ") to IBAN(" + finalAccount.getIban() + ").");

        // validate the two amounts are in the same currency.
        if(!validateSameCurrency(baseAccount.getBalance(), finalAccount.getBalance())) {
            log.warn("BASICACCOUNT_SERVICE:validateAccountsBeforeTransfer: The currencies are NOT the same.");
            throw new NotValidDataException("The currencies are NOT the same. Cancelling operation");
        }

        // validate baseAccount have enough founds.
        if( !validateFunds(baseAccount.getBalance(), finalAccount.getBalance())) {
            log.warn("BASICACCOUNT_SERVICE:validateAccountsBeforeTransfer: The origin account does not have funds to process this transaction.");
            throw new NotValidDataException("The origin account does not have funds to process this transaction.");
        }

        if (!execTransference(baseAccount, finalAccount, amount)) {
            log.error("BASICACCOUNT_SERVICE:validateAccountsBeforeTransfer: The transaction was not complete, unsuccessfully!!");
            throw new NotValidDataException("The transaction was not complete, unsuccessfully!!");
        }

        return FINALIZATION_MESSAGE;
    }

    /*
        Method to EXECUTE the transfer and save the verified transaction and entities to the DB.
     */
    @Transactional
    public boolean execTransference(BasicAccount baseAccount, BasicAccount finalAccount, Money amount) {
        log.info("BASICACCOUNT_SERVICE:execTransference: Executing the transference NOW!!");

        baseAccount.setBalance(new Money(baseAccount.getBalance().decreaseAmount(amount)));

        finalAccount.setBalance(new Money(finalAccount.getBalance().increaseAmount(amount)));

        BasicAccount savedBaseAccount = basicAccountRepository.save(baseAccount);
        BasicAccount savedFinalAccount = basicAccountRepository.save(finalAccount);

        if (!savedBaseAccount.getBalance().equals(baseAccount.getBalance()) || !savedFinalAccount.getBalance().equals(finalAccount.getBalance())) {
            log.error("BASICACCOUNT_SERVICE:execTransference: The data store in the DB is different to the actual data!!");
            throw new NotValidDataException("The data store in the DB is different to the actual data!!");
        }
        return true;
    }




}
