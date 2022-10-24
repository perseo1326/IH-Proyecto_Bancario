package com.josko.proyecto_bancario.repositories;

import com.josko.proyecto_bancario.models.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {



}
