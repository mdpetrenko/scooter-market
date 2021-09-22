package com.github.mdpetrenko.market.services.interfaces;

import com.github.mdpetrenko.market.dtos.OrderDetailsDto;
import com.github.mdpetrenko.market.model.Order;

import java.security.Principal;
import java.util.List;

public interface OrderService {
    void createOrder(OrderDetailsDto orderDetails, Principal principal);

    List<Order> findUserOrders(String username);
}
