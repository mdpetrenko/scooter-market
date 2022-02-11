package com.github.mdpetrenko.market.auth.services.interfaces;

import com.github.mdpetrenko.market.auth.entities.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(String name);
    Optional<Role> getStandardUserRoles();
}
