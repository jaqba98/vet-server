package com.olejarczykjakub.vet_server.repository;

import com.olejarczykjakub.vet_server.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByHomeAccountId(String homeAccountId);
}
