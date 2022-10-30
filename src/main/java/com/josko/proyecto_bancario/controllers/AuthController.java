package com.josko.proyecto_bancario.controllers;


import com.josko.proyecto_bancario.enums.RoleEnum;
import com.josko.proyecto_bancario.models.Role;
import com.josko.proyecto_bancario.models.User;
import com.josko.proyecto_bancario.repositories.RoleRepository;
import com.josko.proyecto_bancario.repositories.UserRepository;
import com.josko.proyecto_bancario.security_pkg.payload.request.LoginRequest;
import com.josko.proyecto_bancario.security_pkg.payload.request.SignupRequest;
import com.josko.proyecto_bancario.security_pkg.payload.response.JwtResponse;
import com.josko.proyecto_bancario.security_pkg.payload.response.MessageResponse;
import com.josko.proyecto_bancario.security_pkg.security.jwt.JwtUtils;
import com.josko.proyecto_bancario.security_pkg.security.services.UserDetailsImpl;
import com.josko.proyecto_bancario.services.ValidatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final ValidatorService validatorService;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(JwtResponse
                .builder()
                .token(jwt)
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .id(userDetails.getId())
                .roles(roles)
                .build());

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getName(),
                                signUpRequest.getUsername(),
                                signUpRequest.getEmail(),
                                encoder.encode(signUpRequest.getPassword()));


        Set<Role> roleSet = new HashSet<>();

        // clean the info from the user and convert it to a valid 'Role' data.
        roleSet = validatorService.validateRoleStringSet(signUpRequest.getRole());

        user.setRoles(roleSet);
        userRepository.save(user);

        // TODO: mejorar el mensaje de respuesta (created - ResponseObjectError.class)???
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
