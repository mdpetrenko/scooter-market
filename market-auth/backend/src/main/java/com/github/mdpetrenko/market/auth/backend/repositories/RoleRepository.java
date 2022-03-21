package com.github.mdpetrenko.market.auth.backend.repositories;

import com.github.mdpetrenko.market.auth.backend.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
