package com.github.mdpetrenko.market.controllers.v1;

import com.github.mdpetrenko.market.dtos.StringResponse;
import com.github.mdpetrenko.market.model.Cart;
import com.github.mdpetrenko.market.services.interfaces.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping("/generate")
    public ResponseEntity<?> generateCartUuid() {
        return ResponseEntity.ok(new StringResponse(UUID.randomUUID().toString()));
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<?> getCartForCurrentUser(@PathVariable UUID cartId, Principal principal) {
        Cart cart = cartService.getCartForCurrentUser(principal, cartId);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/{cartId}/add/{productId}")
    public void addItem(@PathVariable UUID cartId, @PathVariable Long productId, Principal principal) {
        cartService.addItem(principal, cartId, productId);
    }

    @GetMapping("/{cartId}/remove/{productId}")
    public void removeItem(@PathVariable UUID cartId, @PathVariable Long productId, Principal principal) {
        cartService.removeItem(principal, cartId, productId);
    }

    @GetMapping("/{cartId}/decrement/{productId}")
    public void decrementItem(@PathVariable UUID cartId, @PathVariable Long productId, Principal principal) {
        cartService.decrementItem(principal, cartId, productId);
    }

    @GetMapping("/{cartId}/merge")
    public void mergeCarts(@PathVariable UUID cartId, Principal principal) {
        cartService.merge(principal, cartId);
    }

    @GetMapping("/{cartId}/clear")
    public void clearCart(@PathVariable UUID cartId, Principal principal) {
        cartService.clearCart(principal, cartId);
    }

}
