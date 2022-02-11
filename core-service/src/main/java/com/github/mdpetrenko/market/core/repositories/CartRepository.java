package com.github.mdpetrenko.market.core.repositories;

import com.github.mdpetrenko.market.core.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, String> {
}
