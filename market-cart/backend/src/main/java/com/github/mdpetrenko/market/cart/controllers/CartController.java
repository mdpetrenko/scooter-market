package com.github.mdpetrenko.market.cart.controllers;

import com.github.mdpetrenko.market.api.dto.StringResponse;
import com.github.mdpetrenko.market.cart.CartDto;
import com.github.mdpetrenko.market.cart.converters.CartConverter;
import com.github.mdpetrenko.market.cart.entities.Cart;
import com.github.mdpetrenko.market.cart.services.interfaces.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/generate")
    public StringResponse generateCartUuid() {
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{cartId}")
    public CartDto getCartForCurrentUser(@PathVariable UUID cartId, @RequestHeader(required = false) String username) {
        Cart cart = cartService.getCartForCurrentUser(username, cartId);
        return cartConverter.entityToDto(cart);
    }

    @GetMapping("/{cartId}/add/{productId}")
    public void addItem(@PathVariable UUID cartId, @PathVariable Long productId, @RequestHeader(required = false) String username) {
        cartService.addItem(username, cartId, productId);
    }

    @GetMapping("/{cartId}/remove/{productId}")
    public void removeItem(@PathVariable UUID cartId, @PathVariable Long productId, @RequestHeader(required = false) String username) {
        cartService.removeItem(username, cartId, productId);
    }

    @GetMapping("/{cartId}/decrement/{productId}")
    public void decrementItem(@PathVariable UUID cartId, @PathVariable Long productId, @RequestHeader(required = false) String username) {
        cartService.decrementItem(username, cartId, productId);
    }

    @GetMapping("/{cartId}/merge")
    public void mergeCarts(@PathVariable UUID cartId, @RequestHeader(required = false) String username) {
        cartService.merge(username, cartId);
    }

    @GetMapping("/{cartId}/clear")
    public void clearCart(@PathVariable UUID cartId, @RequestHeader(required = false) String username) {
        cartService.clearCart(username, cartId);
    }

}
