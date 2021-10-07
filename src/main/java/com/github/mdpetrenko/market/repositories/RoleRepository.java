package com.github.mdpetrenko.market.repositories;

import com.github.mdpetrenko.market.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String nanme);
}
