package com.github.mdpetrenko.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
public class RegisterRequest {

    private String username;

    private String password;

    private String confirmPassword;

    private String email;

    private Collection<String> roles;

    public boolean isValid() {
        return password.equals(confirmPassword);
    }

}
