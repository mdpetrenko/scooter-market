package com.github.mdpetrenko.market.services;

import com.github.mdpetrenko.market.model.Role;
import com.github.mdpetrenko.market.repositories.RoleRepository;
import com.github.mdpetrenko.market.services.interfaces.RoleService;
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
}
