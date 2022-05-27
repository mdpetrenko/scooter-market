package com.github.mdpetrenko.market.core.backend.services.interfaces;

import com.github.mdpetrenko.market.core.api.commons.OrderStatus;
import com.github.mdpetrenko.market.core.api.dto.OrderDetailsDto;
import com.github.mdpetrenko.market.core.backend.entities.Order;

import java.util.List;

public interface OrderService {
    void createOrder(OrderDetailsDto orderDetails, String username);

    void changeOrderStatus(Long orderId, OrderStatus status);

    List<Order> findOrdersByUsername(String username);

    Order findById(Long id);

}
