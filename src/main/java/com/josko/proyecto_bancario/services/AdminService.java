package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.DTOs.ThirdPartyDTO;
import com.josko.proyecto_bancario.models.ThirdParty;
import com.josko.proyecto_bancario.repositories.ThirdPartyReposiyory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final ThirdPartyReposiyory thirdPartyReposiyory;

    public ThirdParty createThirdPartyUser(ThirdPartyDTO newThirdPartyDTO) {
        log.info("SERVICE:ADMINSERVICE:createThirdPartyUser: Creating a new ThirdParty user.");

        ThirdParty thirdPartyUser = new ThirdParty(newThirdPartyDTO.getName(),newThirdPartyDTO.getHashedKey());
        return thirdPartyReposiyory.save((thirdPartyUser));
    }



}
