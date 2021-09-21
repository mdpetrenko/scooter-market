package com.github.mdpetrenko.market.services;

import com.github.mdpetrenko.market.dtos.OrderDetailsDto;
import com.github.mdpetrenko.market.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.model.Order;
import com.github.mdpetrenko.market.repositories.OrderRepository;
import com.github.mdpetrenko.market.services.interfaces.OrderService;
import com.github.mdpetrenko.market.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;

    @Override
    public Order save(OrderDetailsDto orderDetails) {
        Order order = new Order(orderDetails);
        if (orderDetails.getOwnerId() != null) {
            order.setOwner(userService.findById(orderDetails.getOwnerId())
                    .orElseThrow(() -> new ResourceNotFoundException("User with id=" + orderDetails.getOwnerId() + " not found")));
        }
        return orderRepository.save(order);
    }
}
