package com.josko.proyecto_bancario.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Checking extends JpaRepository<Checking, Long> {



}
