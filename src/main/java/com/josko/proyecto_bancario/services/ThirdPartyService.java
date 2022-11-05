package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.DTOs.SendTranferThirdParty;
import com.josko.proyecto_bancario.DTOs.ThirdPartyDTO;
import com.josko.proyecto_bancario.enums.RoleEnum;
import com.josko.proyecto_bancario.exeptions.IdNotFoundExeption;
import com.josko.proyecto_bancario.exeptions.NotValidDataException;
import com.josko.proyecto_bancario.models.Account;
import com.josko.proyecto_bancario.models.BasicAccount;
import com.josko.proyecto_bancario.models.Money;
import com.josko.proyecto_bancario.models.ThirdParty;
import com.josko.proyecto_bancario.repositories.ThirdPartyReposiyory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ThirdPartyService {

    private final String FINALIZATION_MESSAGE = "Transaction successfully!";

    private final ValidatorService validatorService;
    private final ThirdPartyReposiyory thirdPartyReposiyory;
    private final UserService userService;
    private final BasicAccountService basicAccountService;

    /*
        POST: Create a new ThirdParty user.
     */
    public ThirdParty createNewThirdPartyUser(ThirdPartyDTO newThirdPartyDTO) {
        log.info("THIRDPARTYSERVICE:createNewThirdPartyUser: Creating a new ThirdParty user.");

        ThirdParty thirdPartyUser = new ThirdParty(newThirdPartyDTO.getName(),
                newThirdPartyDTO.getUsername(),
                newThirdPartyDTO.getEmail(),
                newThirdPartyDTO.getPassword(),
                newThirdPartyDTO.getHashedKey());

        thirdPartyUser = (ThirdParty) userService.createNewUser(thirdPartyUser, RoleEnum.ROL_THIRDPARTY);

        return thirdPartyReposiyory.save(thirdPartyUser);
    }

    /*
        Method to get the authenticated 'ThirdParty' user
     */
    private ThirdParty geThirPartyAutenticatedUSer() {

        String username = validatorService.getUserAuthenticated();

        Optional<ThirdParty> thirdPartyUser = thirdPartyReposiyory.findByUsernameIgnoreCase(username.toLowerCase());

        if (thirdPartyUser.isEmpty()) {
            log.warn("THIRDPARTY_SERVICE:getThirdPartyUser: The username(" + username + ") is not registered");
            throw new IdNotFoundExeption(username);
        }
        log.info("THIRDPARTY_SERVICE:geThirPartyAutenticatedUSer: The 'ThirdParty' user(" + username + ") just start session.");
        return thirdPartyUser.get();
    }

    /*
        Method for bring the welcome to the system at a 'ThirdParty' user
     */
    public String thirdPartyUserWelcome() {

        ThirdParty thirdPartyUser = geThirPartyAutenticatedUSer();

        return "Welcome Mr./Ms " + thirdPartyUser.getName() + " to our modern banking system";
    }

    /*
        Method to perform transference between a 'ThirdParty' user and an 'AccountHolder'
     */
    public String sendTransferToAccountHolder(SendTranferThirdParty sendTranferThirdParty) {
        log.info("THIRDPARTY_SERVICE:sendTransferToAccountHolder: Initiating transfer to send from 'ThirdParty' user to an 'AccountHolder'");

        ThirdParty thirdPartyUser = geThirPartyAutenticatedUSer();

        if (sendTranferThirdParty.getBalance().getAmount().compareTo(new BigDecimal(0)) <= 0 ) {
            log.warn("THIRDPARTY_SERVICE:sendTransferToAccountHolder: The amount is not a valid value.");
            throw new NotValidDataException("The amount is not a valid value.");
        }

        if (!thirdPartyUser.getHashedKey().toLowerCase().equals(sendTranferThirdParty.getHasehdKey().toLowerCase())) {
            log.warn("THIRDPARTY_SERVICE:sendTransferToAccountHolder: The hashed key provided is not valid.");
            throw new NotValidDataException("The hashed key provided is not valid.");
        }

        Account destinationAccount = (Account) basicAccountService.getAccountFromIban(sendTranferThirdParty.getIban());

        if (!sendTranferThirdParty.getAccountSecretKey().toLowerCase().equals(destinationAccount.getSecretKey().toLowerCase())) {
            log.warn("THIRDPARTY_SERVICE:sendTransferToAccountHolder: The Secret Account Key provided is not valid.");
            throw new NotValidDataException("The Secret Account Key provided is not valid");
        }

        if(!sendTranferThirdParty.getBalance().getCurrency().equals(destinationAccount.getBalance().getCurrency())) {
            log.warn("THIRDPARTY_SERVICE:sendTransferToAccountHolder: The currencies are different, operation cancelled.");
            throw new NotValidDataException("The currencies are different, operation cancelled");
        }

        destinationAccount.setBalance(new Money(
                destinationAccount.getBalance().increaseAmount(
                        new Money(
                                sendTranferThirdParty.getBalance().getAmount(), sendTranferThirdParty.getBalance().getCurrency()))));
        BasicAccount savedAccount = basicAccountService.save(destinationAccount);

        if (!savedAccount.getBalance().getAmount().equals(destinationAccount.getBalance().getAmount())) {
            log.error("THIRDPARTY_SERVICE:sendTransferToAccountHolder: The transfer was made, but ended with ERROR.");
            throw new NotValidDataException("The transfer was made, but ended with ERROR.");
        }

        return FINALIZATION_MESSAGE;
    }
}
