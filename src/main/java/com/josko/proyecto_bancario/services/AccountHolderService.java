package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.DTOs.AccountHolderDTO;
import com.josko.proyecto_bancario.enums.RoleEnum;
import com.josko.proyecto_bancario.exeptions.IdNotFoundExeption;
import com.josko.proyecto_bancario.exeptions.IdNotValidExeption;
import com.josko.proyecto_bancario.exeptions.NotValidDataException;
import com.josko.proyecto_bancario.models.AccountHolder;
import com.josko.proyecto_bancario.models.Address;
import com.josko.proyecto_bancario.repositories.AccountHolderRepository;
import com.josko.proyecto_bancario.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountHolderService {

    private final UserService userService;
    private final AccountHolderRepository accountHolderRepository;
    private final AddressRepository addressRepository;


    public AccountHolder createNewAccountHolderUser(AccountHolderDTO newAccountHolderDTO) {
        log.info("ACCOUNTHOLDER_SERVICE:createNewAccountHolderUser: Creating a new AccountHolder user.");

        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setName(newAccountHolderDTO.getName());
        accountHolder.setUsername(newAccountHolderDTO.getUsername());
        accountHolder.setEmail(newAccountHolderDTO.getEmail());
        accountHolder.setPassword(newAccountHolderDTO.getPassword());
        accountHolder.setBirthDate(newAccountHolderDTO.getBirthDate());

        Address mainAddress = new Address(newAccountHolderDTO.getMainAddress());
        mainAddress = addressRepository.save(mainAddress);
        accountHolder.setMainAddress(mainAddress);

        if (newAccountHolderDTO.getSecondaryAddress().isPresent()) {
            Address secondaryAddress = new Address(newAccountHolderDTO.getSecondaryAddress().get());
            secondaryAddress = addressRepository.save(secondaryAddress);
            accountHolder.setSecondaryAddress(secondaryAddress);
        }

        accountHolder = (AccountHolder) userService.createNewUser(accountHolder, RoleEnum.ROL_USER);
        return accountHolderRepository.save(accountHolder);
    }

    public List<AccountHolder> findAllAccountHolders() {

        return accountHolderRepository.findAll();
    }

    public List<AccountHolder> getAccountHolderById(Long userId) {

        Optional<AccountHolder> user = accountHolderRepository.findById(userId);
        if (user.isEmpty()) {
            log.warn("ACCOUNTHOLDER_SERVICE:getAccountHolderById: The given ID(" + userId.toString() + ") does not register.");
            throw new IdNotValidExeption(userId.toString());
        }

        List<AccountHolder> users = new ArrayList<>();
        users.add(user.get());
        return users;
    }

    public List<AccountHolder> getAccountHolderByName(String name) {

        List<AccountHolder> users = accountHolderRepository.findByNameContainsOrderByName(name);
        if (users.isEmpty()) {
            log.warn("ACCOUNTHOLDER_SERVICE:getAccountHolderByName: The given name(" + name + ") was not found.");
            throw new NotValidDataException("The search with the name '" + name + "' did not give any results.");
        }
        return users;
    }

    public List<AccountHolder> findByBirthDateValues(Optional<LocalDate> initialDate, Optional<LocalDate> finalDate) {

        // TODO: no se puede usar sentencias ELSE_IF debido a que el metodo DEBE contener un return al final.
        if (initialDate.isEmpty() && finalDate.isEmpty() ) {
            log.warn("ACCOUNTHOLDER_SERVICE:findByBirthDateValues: No Date values provided for search.");
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
        Method to validate a Long ID into a main or primary AccountHolder.
    */
    public Optional<AccountHolder> getMainAccountHolder(Long userId) {
        log.info("VALIDATOR_SERVICE:getMainAccountHolder: Validating AccountHolder from ID.");

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
        log.info("VALIDATOR_SERVICE:getSecondaryAccountHolder: Validating a secondary AccountHolder.");

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




}
