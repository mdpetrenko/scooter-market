package com.github.mdpetrenko.market.cart.converters;

import com.github.mdpetrenko.market.cart.dto.CartDto;
import com.github.mdpetrenko.market.cart.dto.CartItemDto;
import com.github.mdpetrenko.market.cart.entities.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {
    private final CartItemConverter cartItemConverter;

    public CartDto entityToDto(Cart cart) {
        List<CartItemDto> items = cart.getItems().stream().map(cartItemConverter::entityToDto).collect(Collectors.toList());
        return new CartDto(items, cart.getTotalPrice(), cart.getId());
    }
}
