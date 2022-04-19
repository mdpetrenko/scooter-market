package com.github.mdpetrenko.market.auth.backend.services.interfaces;

import com.github.mdpetrenko.market.auth.backend.entities.Role;

public interface RoleService {
    Role findByTitle(String name);
    Role getStandardUserRoles();
}
