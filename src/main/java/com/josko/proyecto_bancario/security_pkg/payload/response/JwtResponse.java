package com.josko.proyecto_bancario.security_pkg.payload.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class JwtResponse {
    private final String type = "Bearer";

    private String token;
    private Long id;
    private String username;
    private String email;
    private List<String> roles;
}
