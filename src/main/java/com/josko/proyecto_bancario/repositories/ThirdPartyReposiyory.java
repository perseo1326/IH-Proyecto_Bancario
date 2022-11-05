package com.josko.proyecto_bancario.repositories;

import com.josko.proyecto_bancario.models.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThirdPartyReposiyory extends JpaRepository<ThirdParty, Long> {

    Optional<ThirdParty> findByUsernameIgnoreCase(String username);

}
