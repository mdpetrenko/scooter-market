package com.github.mdpetrenko.market.auth.backend.services.interfaces;

import com.github.mdpetrenko.market.auth.api.dto.BillingAddressDto;
import com.github.mdpetrenko.market.auth.api.dto.RegisterRequest;
import com.github.mdpetrenko.market.auth.backend.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

   User saveUser(User user);

    Optional<User> findById(Long ownerId);

    Optional<User> findUserWithAddresses(String username);

    void removeUserAddress(String username, Long addressId);

    void addUserAddress(String username, BillingAddressDto addressDto);
}
