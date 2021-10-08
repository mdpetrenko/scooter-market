package com.github.mdpetrenko.market.controllers.v1;

import com.github.mdpetrenko.market.dtos.AuthRequest;
import com.github.mdpetrenko.market.dtos.AuthResponse;
import com.github.mdpetrenko.market.dtos.RegisterRequest;
import com.github.mdpetrenko.market.dtos.UserDto;
import com.github.mdpetrenko.market.exceptions.DataValidationException;
import com.github.mdpetrenko.market.exceptions.MarketError;
import com.github.mdpetrenko.market.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.services.interfaces.UserService;
import com.github.mdpetrenko.market.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new MarketError("Invalid username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> tryToRegister(@RequestBody RegisterRequest registerRequest) {
        if (!registerRequest.isValid()) {
            return new ResponseEntity<>(new MarketError("Passwords are not equal"), HttpStatus.BAD_REQUEST);
        }
        userService.registerUser(registerRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<?> showProfile(Principal principal) {
        UserDto userDto = new UserDto(userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User not found: " + principal.getName())));
        return ResponseEntity.ok(userDto);
    }

}
