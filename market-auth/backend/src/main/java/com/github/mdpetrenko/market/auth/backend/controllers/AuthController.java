package com.github.mdpetrenko.market.auth.backend.controllers;

import com.github.mdpetrenko.market.auth.api.dto.AuthRequest;
import com.github.mdpetrenko.market.auth.api.dto.AuthResponse;
import com.github.mdpetrenko.market.auth.api.dto.RegisterRequest;
import com.github.mdpetrenko.market.auth.backend.services.interfaces.AuthService;
import com.github.mdpetrenko.market.auth.backend.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {
    private final AuthService authService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        UserDetails userDetails = authService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> tryToRegister(@RequestBody RegisterRequest registerRequest) {
        if (!registerRequest.isValid()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        authService.registerUser(registerRequest);
        return ResponseEntity.ok(null);
    }

}
