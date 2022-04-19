package com.github.mdpetrenko.market.auth.backend.services;

import com.github.mdpetrenko.market.api.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.auth.api.dto.BillingAddressDto;
import com.github.mdpetrenko.market.auth.api.dto.UserDto;
import com.github.mdpetrenko.market.auth.backend.converters.BillingAddressConverter;
import com.github.mdpetrenko.market.auth.backend.entities.BillingAddress;
import com.github.mdpetrenko.market.auth.backend.entities.User;
import com.github.mdpetrenko.market.auth.backend.repositories.UserRepository;
import com.github.mdpetrenko.market.auth.backend.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BillingAddressConverter billingAddressConverter;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Username not found: " + username));
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(String username, UserDto userDto) {
        User user = findByUsername(username);
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        return saveUser(user);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found. ID: " + userId));
    }

    @Override
    @Transactional
    public User findUserWithAddresses(String username) {
        return userRepository.findByUsernameWithAddresses(username)
                .orElseThrow(() -> new ResourceNotFoundException("username not found: " + username));
    }

    @Override
    @Transactional
    public void removeUserAddress(String username, Long addressId) {
        User user = findUserWithAddresses(username);
        user.getAddresses().removeIf(a -> Objects.equals(a.getId(), addressId));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void addUserAddress(String username, BillingAddressDto addressDto) {
        User user = findByUsername(username);
        BillingAddress billingAddress = billingAddressConverter.dtoToEntity(addressDto);
        billingAddress.setUser(user);
        user.getAddresses().add(billingAddress);
        saveUser(user);
    }


}
