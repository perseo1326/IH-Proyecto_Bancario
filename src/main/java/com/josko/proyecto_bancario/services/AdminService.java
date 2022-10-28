package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.DTOs.AccountHolderDTO;
import com.josko.proyecto_bancario.DTOs.ThirdPartyDTO;
import com.josko.proyecto_bancario.exeptions.IdNotFoundExeption;
import com.josko.proyecto_bancario.exeptions.IdNotValidExeption;
import com.josko.proyecto_bancario.exeptions.NotValidDataException;
import com.josko.proyecto_bancario.models.*;
import com.josko.proyecto_bancario.repositories.AccountHolderRepository;
import com.josko.proyecto_bancario.repositories.AddressRepository;
import com.josko.proyecto_bancario.repositories.ThirdPartyReposiyory;
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

    private final AddressRepository addressRepository;
    private final ThirdPartyReposiyory thirdPartyReposiyory;
    private final AccountHolderRepository accountHolderRepository;

    /*
        GET AccountHolders By ID
     */
    public List<AccountHolder> getAccountHolderById(Long userId) {

        Optional<AccountHolder> user = accountHolderRepository.findById(userId);
        if (user.isEmpty()) {
            log.warn("SERVICE:ADMINSERVICE:getAccountHolderById: The given ID(" + userId.toString() + ") was not found.");
            throw new IdNotValidExeption(userId.toString());
        }

        List<AccountHolder> users = new ArrayList<>();
        users.add(user.get());
        return users;
    }

    // GET AccountHolders By name
    public List<AccountHolder> getAccountHolderByName(String name) {

        List<AccountHolder> users = accountHolderRepository.findByNameContainsOrderByName(name);
        if (users.isEmpty()) {
            log.warn("SERVICE:ADMINSERVICE:getAccountHolderByName: The given name(" + name + ") was not found.");
            throw new IdNotFoundExeption(name);
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
            log.warn("SERVICE:ADMINSERVICE:findByBirthDateValues: No Date values provided for search.");
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
        log.info("SERVICE:ADMINSERVICE:createAccountHolderUser: Creating a new AccountHolder user.");

        Address mainAddress = new Address();
        mainAddress.clone(newAccountHolderDTO.getMainAddress());

        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setName(newAccountHolderDTO.getName());
        accountHolder.setBirthDate(newAccountHolderDTO.getBirthDate());
        accountHolder.setMainAddress(addressRepository.save(newAccountHolderDTO.getMainAddress()));

        if (newAccountHolderDTO.getSecondaryAddress().isPresent()) {
            Address mailingAddress = new Address();
            mailingAddress.clone(newAccountHolderDTO.getSecondaryAddress().get());

            accountHolder.setSecondaryAddress(addressRepository.save(newAccountHolderDTO.getSecondaryAddress().get()));
        }

        return accountHolderRepository.save(accountHolder);
    }

    /*
        CREATE ThirdParty entities in the DB.
     */
    public ThirdParty createThirdPartyUser(ThirdPartyDTO newThirdPartyDTO) {
        log.info("SERVICE:ADMINSERVICE:createThirdPartyUser: Creating a new ThirdParty user.");

        ThirdParty thirdPartyUser = new ThirdParty(newThirdPartyDTO.getName(), newThirdPartyDTO.getHashedKey());
        return thirdPartyReposiyory.save(thirdPartyUser);
    }




}
