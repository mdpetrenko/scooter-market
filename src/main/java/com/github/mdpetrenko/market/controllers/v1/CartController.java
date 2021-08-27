package com.github.mdpetrenko.market.controllers.v1;

import com.github.mdpetrenko.market.dtos.ProductDto;
import com.github.mdpetrenko.market.services.interfaces.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping
    public List<ProductDto> getCartContent() {
        return cartService.getCartContent().stream()
                .map(ProductDto::new).collect(Collectors.toList());
    }

    @PostMapping
    public void addToCart(@RequestBody ProductDto productDto) {
        cartService.addProductById(productDto.getId());
    }

    @DeleteMapping("/{id}")
    public void removeFromCart(@PathVariable Long id) {
        cartService.removeProductById(id);
    }

}
