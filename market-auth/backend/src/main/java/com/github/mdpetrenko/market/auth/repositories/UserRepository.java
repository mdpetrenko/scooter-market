package com.github.mdpetrenko.market.auth.repositories;

import com.github.mdpetrenko.market.auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
