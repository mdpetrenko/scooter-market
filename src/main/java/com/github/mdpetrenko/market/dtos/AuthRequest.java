package com.github.mdpetrenko.market.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AuthRequest {
    private String username;
    private String password;
}
