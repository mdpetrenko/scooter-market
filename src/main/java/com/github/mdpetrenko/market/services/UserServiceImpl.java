package com.github.mdpetrenko.market.services;

import com.github.mdpetrenko.market.dtos.RegisterRequest;
import com.github.mdpetrenko.market.dtos.UserDto;
import com.github.mdpetrenko.market.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.model.Role;
import com.github.mdpetrenko.market.model.User;
import com.github.mdpetrenko.market.repositories.UserRepository;
import com.github.mdpetrenko.market.services.interfaces.RoleService;
import com.github.mdpetrenko.market.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public void registerUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail());
        if (registerRequest.getRoles() != null) {
            user.setRoles(registerRequest.getRoles().stream()
                    .map(r -> roleService.findByName(r)
                            .orElseThrow(() -> new ResourceNotFoundException("Role not found: " + r)))
                    .collect(Collectors.toList()));
        } else {
            user.setRoles(List.of(roleService.getStandardUserRoles().orElseThrow()));
        }
        userRepository.save(user);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
