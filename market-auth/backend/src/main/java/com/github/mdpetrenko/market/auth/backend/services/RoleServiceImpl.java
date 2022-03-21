package com.github.mdpetrenko.market.auth.backend.services;

import com.github.mdpetrenko.market.auth.backend.entities.Role;
import com.github.mdpetrenko.market.auth.backend.repositories.RoleRepository;
import com.github.mdpetrenko.market.auth.backend.services.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Optional<Role> getStandardUserRoles() {
        return findByName("ROLE_USER");
    }
}
