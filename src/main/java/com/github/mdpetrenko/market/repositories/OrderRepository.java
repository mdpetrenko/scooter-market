package com.github.mdpetrenko.market.repositories;

import com.github.mdpetrenko.market.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
