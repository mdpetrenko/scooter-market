package com.github.mdpetrenko.market.repositories;

import com.github.mdpetrenko.market.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, String> {
}
