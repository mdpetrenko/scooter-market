package com.github.mdpetrenko.market.core.services.interfaces;

import com.github.mdpetrenko.market.core.dtos.OrderDetailsDto;
import com.github.mdpetrenko.market.core.model.Order;

import java.security.Principal;
import java.util.List;

public interface OrderService {
    void createOrder(OrderDetailsDto orderDetails, String username);

    List<Order> findUserOrders(String username);
}
