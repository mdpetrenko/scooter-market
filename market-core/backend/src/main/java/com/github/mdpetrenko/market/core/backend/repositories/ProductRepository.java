package com.github.mdpetrenko.market.core.backend.repositories;

import com.github.mdpetrenko.market.core.backend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
}
