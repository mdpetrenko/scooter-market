package com.github.mdpetrenko.market.auth.backend.repositories;

import com.github.mdpetrenko.market.auth.backend.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Query("select u from User u where u.username = :username")
    @EntityGraph(value = "user.front")
    Optional<User> findByUsernameWithAddresses(@Param("username") String username);

}
