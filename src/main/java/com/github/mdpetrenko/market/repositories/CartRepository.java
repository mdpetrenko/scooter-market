package com.github.mdpetrenko.market.repositories;

import com.github.mdpetrenko.market.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, String> {
}
