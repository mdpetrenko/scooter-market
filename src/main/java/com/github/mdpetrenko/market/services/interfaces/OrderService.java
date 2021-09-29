package com.github.mdpetrenko.market.services.interfaces;

import com.github.mdpetrenko.market.dtos.OrderDetailsDto;
import com.github.mdpetrenko.market.model.Order;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

public interface OrderService {
    void createOrder(OrderDetailsDto orderDetails, Principal principal, UUID uuid);

    List<Order> findUserOrders(String username);
}
