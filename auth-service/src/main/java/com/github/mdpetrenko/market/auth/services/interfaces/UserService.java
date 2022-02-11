package com.github.mdpetrenko.market.auth.services.interfaces;

import com.github.mdpetrenko.market.auth.dto.RegisterRequest;
import com.github.mdpetrenko.market.auth.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> findByUsername(String username);
    void registerUser(RegisterRequest registerRequest);

    Optional<User> findById(Long ownerId);
}
