package com.github.mdpetrenko.market;

import com.github.mdpetrenko.market.model.Cart;
import com.github.mdpetrenko.market.model.Product;
import com.github.mdpetrenko.market.repositories.CartRepository;
import com.github.mdpetrenko.market.services.CartServiceImpl;
import com.github.mdpetrenko.market.services.interfaces.CartService;
import com.github.mdpetrenko.market.services.interfaces.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest(classes = {CartServiceImpl.class})
public class CartServiceTest {
    @Autowired
    private CartService cartService;

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private ProductService productService;

    @Test
    public void addToCartTest() {
        UUID uuid = UUID.randomUUID();
        Product product = new Product();
        product.setTitle("Bread");
        product.setPrice(100);
        product.setId(1L);
        Cart repoCart = new Cart();
        repoCart.setId("ScooterMarket_Cart_" + uuid);
        Mockito.doReturn(Optional.of(repoCart))
                .when(cartRepository)
                .findById("ScooterMarket_Cart_" + uuid);
        Mockito.doReturn(Optional.of(product))
                .when(productService)
                .findById(1L);
        cartService.addItem(null, uuid, 1L);
        cartService.addItem(null, uuid, 1L);
        cartService.addItem(null, uuid, 1L);
        Cart cart = cartService.getCartForCurrentUser(null, uuid);

        Assertions.assertEquals(3, cart.getItems().stream().filter(i -> i.getProductId().equals(1L)).findFirst().get().getQuantity());
        Mockito.verify(productService, Mockito.times(1)).findById(1L);
    }
}
