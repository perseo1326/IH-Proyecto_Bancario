package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.DTOs.SendTranferThirdParty;
import com.josko.proyecto_bancario.DTOs.ThirdPartyDTO;
import com.josko.proyecto_bancario.enums.RoleEnum;
import com.josko.proyecto_bancario.exeptions.IdNotFoundExeption;
import com.josko.proyecto_bancario.models.ThirdParty;
import com.josko.proyecto_bancario.repositories.ThirdPartyReposiyory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ThirdPartyService {

    private final ValidatorService validatorService;
    private final ThirdPartyReposiyory thirdPartyReposiyory;
    private final UserService userService;

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



        return "Nada implementado aun, seguimos trabajando enello.";

    }
}
