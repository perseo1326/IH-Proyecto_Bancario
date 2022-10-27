package com.josko.proyecto_bancario.repositories;

import com.josko.proyecto_bancario.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where upper(u.name) like upper(concat('%', :name, '%')) order by u.name")
    List<User> findByNameContainsOrderByName(@Param("name") String name);



}
