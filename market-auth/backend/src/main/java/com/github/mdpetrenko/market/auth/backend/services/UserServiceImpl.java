package com.github.mdpetrenko.market.auth.backend.services;

import com.github.mdpetrenko.market.api.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.auth.api.dto.BillingAddressDto;
import com.github.mdpetrenko.market.auth.backend.converters.BillingAddressConverter;
import com.github.mdpetrenko.market.auth.backend.entities.BillingAddress;
import com.github.mdpetrenko.market.auth.backend.entities.User;
import com.github.mdpetrenko.market.auth.backend.repositories.UserRepository;
import com.github.mdpetrenko.market.auth.backend.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BillingAddressConverter billingAddressConverter;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    @Transactional
    public Optional<User> findUserWithAddresses(String username) {
        return userRepository.findByUsernameWithAddresses(username);
    }

    @Override
    @Transactional
    public void removeUserAddress(String username, Long addressId) {
        User user = findUserWithAddresses(username).orElseThrow(() -> new ResourceNotFoundException("username not found: " + username));
        user.getAddresses().removeIf(a -> Objects.equals(a.getId(), addressId));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void addUserAddress(String username, BillingAddressDto addressDto) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Username not found: " + username));
        BillingAddress billingAddress = billingAddressConverter.dtoToEntity(addressDto);
        billingAddress.setUser(user);
        user.getAddresses().add(billingAddress);
        saveUser(user);
    }


}
