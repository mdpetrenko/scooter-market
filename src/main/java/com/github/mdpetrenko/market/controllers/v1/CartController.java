package com.github.mdpetrenko.market.controllers.v1;

import com.github.mdpetrenko.market.dtos.ProductDto;
import com.github.mdpetrenko.market.model.Product;
import com.github.mdpetrenko.market.services.interfaces.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping
    public Map<ProductDto, Integer> getAllProducts() {
        Map<ProductDto, Integer> products = new HashMap<>();
        Map<Product, Integer> cartProducts = cartService.getAllProducts();
        for (Product product : cartProducts.keySet()) {
            products.put(new ProductDto(product), cartProducts.get(product));
        }
        return products;
    }

    @PostMapping
    public void addProduct(@RequestBody ProductDto productDto) {
        cartService.addProductById(productDto.getId());
    }

    @DeleteMapping
    public void removeProduct(@RequestBody ProductDto productDto) {
        cartService.removeProductById(productDto.getId());
    }

}
