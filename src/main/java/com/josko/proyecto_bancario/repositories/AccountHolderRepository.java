package com.josko.proyecto_bancario.repositories;

import com.josko.proyecto_bancario.models.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {
    @Query("select a from AccountHolder a where upper(a.name) like upper(concat('%', :name, '%')) order by a.name")
    List<AccountHolder> findByNameContainsOrderByName(@Param("name") String name);



}
