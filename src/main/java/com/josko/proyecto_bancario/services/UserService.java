package com.josko.proyecto_bancario.services;

import com.josko.proyecto_bancario.enums.RoleEnum;
import com.josko.proyecto_bancario.exeptions.NotValidDataException;
import com.josko.proyecto_bancario.models.User;
import com.josko.proyecto_bancario.repositories.RoleRepository;
import com.josko.proyecto_bancario.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User createNewUser(User newUser, RoleEnum role) {
        log.info("USERSERVICE:createNewUser: Creating a new user into the DB.");

        if (userRepository.existsByUsername(newUser.getUsername())) {
            log.warn("USERSERVICE:createNewUser: Username is already registered.");
            throw new NotValidDataException("Username is already registered.");
        }

        if (userRepository.existsByEmail(newUser.getEmail())) {
            log.warn("USERSERVICE:createNewUser: User email is already registered.");
            throw new NotValidDataException("User email is already registered.");
        }

        // Encrypt the password for the new user
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        // clean the info from the user and convert it to a valid 'Role' data.
//        roleSet = validatorService.validateRoleStringSet(signUpRequest.getRole());

        newUser.addRole(roleRepository.findByRoleName(role));

        return newUser;
    }


    public User getUserByUsername(String username) {

        Optional<User> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            log.warn("USER_SERVICE:getUserByUsername: The user with username(" + username + ") is not registered in our database.");
            throw new NotValidDataException("The user with username(\" + username + \") is not registered in our database.");
        }

        return user.get();
    }


}
