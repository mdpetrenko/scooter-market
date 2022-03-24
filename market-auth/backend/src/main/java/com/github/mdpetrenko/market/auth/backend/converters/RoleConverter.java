package com.github.mdpetrenko.market.auth.backend.converters;

import com.github.mdpetrenko.market.auth.backend.entities.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {
    public RoleDto entityToDto(Role role) {
        return new RoleDto(role.getTitle());
    }
}
