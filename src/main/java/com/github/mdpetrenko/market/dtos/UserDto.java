package com.github.mdpetrenko.market.dtos;

import com.github.mdpetrenko.market.model.Role;
import com.github.mdpetrenko.market.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;

    private String username;

    private String password;

    private String email;

    private Collection<String> roles;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
    }
}
