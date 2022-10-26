package com.josko.proyecto_bancario.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {

    public boolean isSecretKeyValid(String secretKey) {

        if (!secretKey.matches("[0-9]{4}"))
            throw new IllegalArgumentException("Not a valid hashed key supplied.");
        return true;
    }


}
