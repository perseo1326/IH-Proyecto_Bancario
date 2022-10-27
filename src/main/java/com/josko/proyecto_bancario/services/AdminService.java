package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.DTOs.ThirdPartyDTO;
import com.josko.proyecto_bancario.enums.AccountTypeEnum;
import com.josko.proyecto_bancario.exeptions.IdNotFoundExeption;
import com.josko.proyecto_bancario.exeptions.IdNotValidExeption;
import com.josko.proyecto_bancario.exeptions.NotValidDataException;
import com.josko.proyecto_bancario.models.*;
import com.josko.proyecto_bancario.repositories.AccountHolderRepository;
import com.josko.proyecto_bancario.repositories.ThirdPartyReposiyory;
import com.josko.proyecto_bancario.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final ThirdPartyReposiyory thirdPartyReposiyory;
    private final AccountHolderRepository accountHolderRepository;

    // CREATE ThirdParty entities
    public ThirdParty createThirdPartyUser(ThirdPartyDTO newThirdPartyDTO) {
        log.info("SERVICE:ADMINSERVICE:createThirdPartyUser: Creating a new ThirdParty user.");

        ThirdParty thirdPartyUser = new ThirdParty(newThirdPartyDTO.getName(),newThirdPartyDTO.getHashedKey());
        return thirdPartyReposiyory.save((thirdPartyUser));
    }

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

    public List<AccountHolder> findAllAccountHolders() {

        return accountHolderRepository.findAll();
    }

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





    public BasicAccount createNewAccount(Map<String, String> values) {
/*
        if (!values.containsKey("accountType") || !AccountTypeEnum.CHECKING.contains(values.get("accountType"))) {
            log.warn("SERVICE:ADMINSERVICE:createNewAccount: No valid accountType selected.");
            throw new IllegalArgumentException("No valid account type selected.");
        }

        if (!values.containsKey("mainOwnerId")) {
            log.warn("SERVICE:ADMINSERVICE:createNewAccount: The values supplied are not valid.");
            throw new IllegalArgumentException("The values supplied are not valid.");
        }

        Optional<User> mainOwner = userRepository.findById(Long.parseLong(values.get("mainOwnerId")));

        if (mainOwner.isEmpty()) {
            log.warn("SERVICE:ADMINSERVICE:createNewAccount: Not a valid user ID provided.");
            throw new IllegalArgumentException("Not a valid user ID provided.");
        }

        // TODO: necesario terminar


        System.out.println("\n" + values);
  */
        return null;

    }



}
