package com.josko.proyecto_bancario.repositories;

import com.josko.proyecto_bancario.models.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {
    @Query("select a from AccountHolder a where upper(a.name) like upper(concat('%', :name, '%')) order by a.name")
    List<AccountHolder> findByNameContainsOrderByName(@Param("name") String name);

    List<AccountHolder> findByBirthDateBetween(LocalDate initialDate, LocalDate finalDate);

    @Query("select a from AccountHolder a where a.birthDate <= :birthDate order by a.name")
    List<AccountHolder> findByBirthDateLessThanEqualOrderByNameAsc(@Param("birthDate") LocalDate birthDate);

    @Query("select a from AccountHolder a where a.birthDate >= :birthDate order by a.name")
    List<AccountHolder> findByBirthDateGreaterThanEqualOrderByNameAsc(@Param("birthDate") LocalDate birthDate);

    Optional<AccountHolder> findByUsernameIgnoreCase(String username);

}
