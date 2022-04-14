package com.github.mdpetrenko.market.auth.backend.controllers;

import com.github.mdpetrenko.market.api.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.auth.api.dto.BillingAddressDto;
import com.github.mdpetrenko.market.auth.api.dto.UserDto;
import com.github.mdpetrenko.market.auth.backend.converters.UserConverter;
import com.github.mdpetrenko.market.auth.backend.entities.User;
import com.github.mdpetrenko.market.auth.backend.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final UserConverter userConverter;

    @GetMapping
    public UserDto getUserInfo(@RequestHeader String username) {
        User user = userService.findUserWithAddresses(username).orElseThrow(() -> new ResourceNotFoundException("Username not found: " + username));
        return userConverter.entityToDto(user);
    }

    @DeleteMapping("/billing/{addressId}")
    public ResponseEntity<?> removeAddress(@RequestHeader String username, @PathVariable Long addressId) {
        userService.removeUserAddress(username, addressId);
        return ResponseEntity.ok(null);
    }

}
