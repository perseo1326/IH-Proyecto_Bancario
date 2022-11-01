package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.enums.RoleEnum;
import com.josko.proyecto_bancario.models.Role;
import com.josko.proyecto_bancario.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidatorService {



    private final RoleRepository roleRepository;


    public boolean isSecretKeyValid(String secretKey) {

        if (!secretKey.matches("[0-9]{4}"))
            throw new IllegalArgumentException("Not a valid hashed key supplied.");
        return true;
    }


    public Set<Role> validateRoleStringSet(Set<String> roleStringSet) {

        Set<Role> roleSet = new HashSet<>();

        for ( String role : roleStringSet) {
            if (RoleEnum.ROL_USER.contains(role.toUpperCase())) {
                roleSet.add( roleRepository.findByRoleName(RoleEnum.ROL_THIRDPARTY.getValue(role.toUpperCase())));
            }
        }

        // No Role was specified, we assign the default: THIRDPARTY
        if (roleSet.isEmpty()){
            roleSet.add( roleRepository.findByRoleName(RoleEnum.ROL_THIRDPARTY));
        }
        return roleSet;
    }




}
