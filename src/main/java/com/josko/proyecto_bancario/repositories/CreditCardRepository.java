package com.josko.proyecto_bancario.repositories;

import com.josko.proyecto_bancario.models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {



}