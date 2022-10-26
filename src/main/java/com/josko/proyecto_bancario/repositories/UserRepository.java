package com.josko.proyecto_bancario.repositories;

import com.josko.proyecto_bancario.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
