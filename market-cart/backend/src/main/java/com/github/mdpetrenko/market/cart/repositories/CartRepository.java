package com.github.mdpetrenko.market.cart.repositories;

import com.github.mdpetrenko.market.cart.entities.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, String> {
}
