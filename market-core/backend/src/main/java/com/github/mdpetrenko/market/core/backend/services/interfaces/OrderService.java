package com.github.mdpetrenko.market.core.backend.services.interfaces;

import com.github.mdpetrenko.market.core.api.dto.OrderDetailsDto;
import com.github.mdpetrenko.market.core.backend.entities.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    void createOrder(OrderDetailsDto orderDetails, String username);
    void changeOrderStatus(Long orderId, Order.OrderStatus status);

    List<Order> findOrdersByUsername(String username);

    Optional<Order> findById(Long id);

}
