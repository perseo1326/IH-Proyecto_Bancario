package com.josko.proyecto_bancario.repositories;

import com.josko.proyecto_bancario.models.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Long> {



}
