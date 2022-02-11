package com.github.mdpetrenko.market.core.repositories;

import com.github.mdpetrenko.market.core.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
