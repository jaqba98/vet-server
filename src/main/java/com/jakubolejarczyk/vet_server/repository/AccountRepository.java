package com.jakubolejarczyk.vet_server.repository;

import com.jakubolejarczyk.vet_server.model.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Account a SET a.role = :role WHERE a.email = :email")
    void updateRole(@Param("email") String email, @Param("role") String role);
}
