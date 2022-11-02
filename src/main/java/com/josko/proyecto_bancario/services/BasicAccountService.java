package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.exeptions.NotValidDataException;
import com.josko.proyecto_bancario.exeptions.SearchWithNoResultsException;
import com.josko.proyecto_bancario.models.AccountHolder;
import com.josko.proyecto_bancario.models.BasicAccount;
import com.josko.proyecto_bancario.repositories.BasicAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BasicAccountService {

    private final BasicAccountRepository basicAccountRepository;


    /*
        Method for search a specific account based on his IBAN
     */
    public BasicAccount getAccountFromIban(String iban) {

        Optional<BasicAccount> basicAccount = basicAccountRepository.findByIbanIgnoreCase(iban);
        if (basicAccount.isEmpty()) {
            throw new SearchWithNoResultsException("The given IBAN(" + iban + ") was not found.");
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
        Show a specific account by his IBAN and owned by the current logged user
     */
    public BasicAccount getAccountFromAccountHolderAndIban(AccountHolder user, String iban) {

        Optional<BasicAccount> account = basicAccountRepository.findByIbanIgnoreCase(iban);

        if (account.isEmpty()){
            log.warn("BASICACCOUNT_SERVICE:getAccountFromAccountHolderAndIban: No accounts were found with the given IBAN(" + iban + ").");
            throw new NotValidDataException("The user " + user.getName() + " do not have any account with an IBAN: " + iban + ".");
        }

        if(!account.get().getFirstAccountHolder().getId().equals(user.getId()) ){
            if( !account.get().getSecondAccountholder().getId().equals(user.getId()) ) {
                log.warn("BASICACCOUNT_SERVICE:getAccountFromAccountHolderAndIban: The user " + user.getName() + " do not have any account with an IBAN: " + iban + ".");
                throw new NotValidDataException("The user " + user.getName() + " do not have any account with an IBAN: " + iban + ".");
            }
        }

        return account.get();
    }





}
