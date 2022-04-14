package com.github.mdpetrenko.market.auth.backend.services.interfaces;

import com.github.mdpetrenko.market.auth.api.dto.BillingAddressDto;
import com.github.mdpetrenko.market.auth.api.dto.UserDto;
import com.github.mdpetrenko.market.auth.backend.entities.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);

    User saveUser(User user);

    User updateUser(String username, UserDto userDto);

    Optional<User> findById(Long ownerId);

    Optional<User> findUserWithAddresses(String username);

    void removeUserAddress(String username, Long addressId);

    void addUserAddress(String username, BillingAddressDto addressDto);

}
