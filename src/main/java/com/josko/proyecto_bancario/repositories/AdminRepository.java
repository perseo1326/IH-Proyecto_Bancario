package com.josko.proyecto_bancario.repositories;

import com.josko.proyecto_bancario.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {




}
