package com.josko.proyecto_bancario.repositories;

import com.josko.proyecto_bancario.enums.RoleEnum;
import com.josko.proyecto_bancario.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("select r from Role r where r.name = :name")
    Optional<Role> findByName(@Param("name") RoleEnum name);

    //    Optional<Role> findByName(RoleEnum name);



}
