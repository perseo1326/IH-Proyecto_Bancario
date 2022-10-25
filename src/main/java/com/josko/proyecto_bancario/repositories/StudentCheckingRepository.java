package com.josko.proyecto_bancario.repositories;

import com.josko.proyecto_bancario.models.StudentChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCheckingRepository extends JpaRepository<StudentChecking, Long> {



}