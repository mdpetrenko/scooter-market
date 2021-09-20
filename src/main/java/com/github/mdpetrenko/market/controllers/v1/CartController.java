package com.github.mdpetrenko.market.controllers.v1;

import com.github.mdpetrenko.market.services.interfaces.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<?> getCartForCurrentUser() {
        return ResponseEntity.ok(cartService.getCartForCurrentUser());
    }

    @GetMapping("/add/{productId}")
    public void addItem(@PathVariable Long productId) {
        cartService.addItem(productId);
    }

    @GetMapping("/remove/{productId}")
    public void removeItem(@PathVariable Long productId) {
        cartService.removeItem(productId);
    }

    @GetMapping("/decrement/{productId}")
    public void decrementItem(@PathVariable Long productId) {
        cartService.decrementItem(productId);
    }

}
