package com.github.mdpetrenko.market.core.backend.repositories;

import com.github.mdpetrenko.market.core.backend.entities.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.username = :username")
    @EntityGraph(value = "orders.front")
    List<Order> findAllByUsername(String username);
}
