package com.github.mdpetrenko.market.services.interfaces;

import com.github.mdpetrenko.market.model.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(String name);
}
