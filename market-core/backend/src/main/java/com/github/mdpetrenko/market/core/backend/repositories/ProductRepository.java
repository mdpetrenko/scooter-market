package com.github.mdpetrenko.market.core.backend.repositories;

import com.github.mdpetrenko.market.core.backend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
