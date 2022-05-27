package com.github.mdpetrenko.market.auth.backend.services;

import com.github.mdpetrenko.market.api.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.auth.backend.entities.Role;
import com.github.mdpetrenko.market.auth.backend.repositories.RoleRepository;
import com.github.mdpetrenko.market.auth.backend.services.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role findByTitle(String title) {
        return roleRepository.findByTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found. Title: " + title));
    }

    @Override
    public Role getStandardUserRoles() {
        return findByTitle("ROLE_USER");
    }
}
