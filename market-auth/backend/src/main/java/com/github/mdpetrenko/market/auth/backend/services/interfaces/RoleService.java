package com.github.mdpetrenko.market.auth.backend.services.interfaces;

import com.github.mdpetrenko.market.auth.backend.entities.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findByTitle(String name);
    Optional<Role> getStandardUserRoles();
}
