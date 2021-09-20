package com.github.mdpetrenko.market.services.interfaces;

import com.github.mdpetrenko.market.dtos.UserDto;
import com.github.mdpetrenko.market.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {

    Optional<User> findByUsername(String username);
    void registerUser(UserDto userDto);

}
