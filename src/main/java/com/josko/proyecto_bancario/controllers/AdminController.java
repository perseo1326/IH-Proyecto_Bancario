package com.josko.proyecto_bancario.controllers;

import com.josko.proyecto_bancario.DTOs.*;
import com.josko.proyecto_bancario.exeptions.NotValidDataException;
import com.josko.proyecto_bancario.models.*;
import com.josko.proyecto_bancario.services.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @RequestMapping("/welcome")
//    @PreAuthorize("hasAuthority('ROL_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public String adminWelcome() {

        return adminService.adminWelcome();
    }

    /*
        POST: Create a new ThirdParty user.
     */
    @PostMapping("/newthirdparty")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty createThirdPartyUser(@Valid @RequestBody ThirdPartyDTO thirdPartyDTO) {

        return adminService.createThirdPartyUser(thirdPartyDTO);
    }

    /*
        POST: Create a new AccountHolder User
     */
    @PostMapping("/newaccountholder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder createAccountHolderUser(@RequestBody @Valid AccountHolderDTO accountHolderDTO) {

        return adminService.createAccountHolderUser(accountHolderDTO);
    }

    /*
        - Obtener un AccountHolder por su ID.
     */
    @GetMapping("/accountholders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> getAccountHoldersById(@PathVariable("id") Optional<Long> accountHolderId) {

        if (accountHolderId.isEmpty()) {
            throw new NotValidDataException("No data present for search");
        }

        return adminService.getAccountHolderById(accountHolderId.get());
    }

    /*
        - Obtener el listado de todos los AccountHolders
        - Obtener el listado de AccountHolders por nombre.
     */
    @GetMapping("/accountholders")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> getAccountHolders_AndByName(@RequestParam("name") Optional<String> userName) {

        if(userName.isPresent() ) {
            return adminService.getAccountHolderByName(userName.get());
        }

        return adminService.findAllAccountHolders();
    }

    /*
        GET: Realizar busquedas por rango de fechas de nacimiento, fecha hasta y desde una fecha determinada.
     */
    @GetMapping("/accountholders/birthdate")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountHolder> getAccountHoldersByBirthDateRange(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> initialDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> finalDate) {

        return adminService.findByBirthDateValues(initialDate, finalDate);
    }

    /*
        POST: Creacion de una nueva cuenta del tipo 'Checking' para el usuario seleccionado
    */
    @PostMapping("/accountholders/{id}/newchecking")
    @ResponseStatus(HttpStatus.CREATED)
    public Checking createNewCheckingAccount(@PathVariable("id") Long userId, @RequestBody CheckingDTO checkingDTO) {

        return adminService.createNewCheckingAccount(userId, checkingDTO);
    }

    /*
        POST: creacion de una cuenta de tipo 'Savings' para el usuario seleccionando
     */
    @PostMapping("/accountholders/{id}/newsavings")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings createNewSavingsAccount(@PathVariable("id") Long userId, @RequestBody SavingsDTO newSavingsDTO) {

        return adminService.createNewSavingsAccount(userId, newSavingsDTO );
    }

    /*
        Method to create a new Credit Card account
     */
    @PostMapping("/accountholders/{id}/newcreditcard")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard createNewCreditCardAccount(@PathVariable("id") Long userId, @RequestBody CreditCardDTO creditCardDTO) {

        return adminService.createNewCreditCardAccount(userId, creditCardDTO);
    }

    /*
        Method to show the accounts that belong to an AccountHolder
    */
    @GetMapping("/accountholders/{id}/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<BasicAccount>  getAllAccountsFromAccountHolderUser(@PathVariable("id") Long userId) {

        return adminService.getAllAccountsFromAccountHolderUser(userId);
    }

    /*
        Method for search a specific account based on his IBAN
    */
    @GetMapping("/accountholders/accounts/{iban}")
    @ResponseStatus(HttpStatus.OK)
    public BasicAccount  getAccountFromAccountHolderByIban(@PathVariable("iban") String iban) {

        return adminService.getAccountFromIban(iban);
    }

    /*
        Method for change the balance of an account, selected by his IBAN and confirmed by his owner
     */
    @PatchMapping("/accountholders/{id}/accounts")
    @ResponseStatus(HttpStatus.OK)
    public BasicAccount changeBalanceBasicInAccount(@PathVariable("id") Long userId, @RequestBody ChangeBalance changeBalance) {

        return adminService.changeBalanceBasicInAccount(userId, changeBalance);
    }

}
