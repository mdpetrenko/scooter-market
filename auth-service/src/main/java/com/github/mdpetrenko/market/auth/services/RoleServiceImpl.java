package com.github.mdpetrenko.market.auth.services;

import com.github.mdpetrenko.market.auth.entities.Role;
import com.github.mdpetrenko.market.auth.repositories.RoleRepository;
import com.github.mdpetrenko.market.auth.services.interfaces.RoleService;
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
