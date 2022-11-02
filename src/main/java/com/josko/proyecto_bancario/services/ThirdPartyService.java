package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.DTOs.ThirdPartyDTO;
import com.josko.proyecto_bancario.enums.RoleEnum;
import com.josko.proyecto_bancario.models.ThirdParty;
import com.josko.proyecto_bancario.repositories.ThirdPartyReposiyory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ThirdPartyService {

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


}
