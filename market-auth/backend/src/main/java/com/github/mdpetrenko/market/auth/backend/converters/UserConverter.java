package com.github.mdpetrenko.market.auth.backend.converters;

import com.github.mdpetrenko.market.auth.api.dto.BillingAddressDto;
import com.github.mdpetrenko.market.auth.api.dto.UserDto;
import com.github.mdpetrenko.market.auth.backend.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserConverter {
    private final BillingAddressConverter billingAddressConverter;

    public UserDto entityToDto(User user) {
        List<BillingAddressDto> billingAddressDtoList = user.getAddresses()
                .stream().map(billingAddressConverter::entityToDto).collect(Collectors.toList());
        return new UserDto(user.getName(), user.getPhone(), user.getEmail(), billingAddressDtoList);
    }
}
