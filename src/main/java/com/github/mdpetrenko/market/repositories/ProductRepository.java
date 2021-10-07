package com.github.mdpetrenko.market.repositories;

import com.github.mdpetrenko.market.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
