package com.josko.proyecto_bancario.repositories;

import com.josko.proyecto_bancario.models.Savings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsRepository extends JpaRepository<Savings, Long> {




}