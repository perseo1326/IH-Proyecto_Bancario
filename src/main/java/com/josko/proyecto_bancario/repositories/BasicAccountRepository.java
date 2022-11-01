package com.josko.proyecto_bancario.repositories;

import com.josko.proyecto_bancario.models.BasicAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BasicAccountRepository extends JpaRepository<BasicAccount, Long > {
    @Query("""
            select b from BasicAccount b
            where b.firstAccountHolder.id = :id or b.secondAccountholder.id = :id1
            order by b.firstAccountHolder.name""")
    List<BasicAccount> findByFirstOrSecondAccountHolderById_OrderedByName(@Param("id") Long id, @Param("id1") Long id1);

    @Query("select b from BasicAccount b where upper(b.iban) = upper(:iban)")
    Optional<BasicAccount> findByIbanIgnoreCase(@Param("iban") String iban);




}
