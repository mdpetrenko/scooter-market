package com.github.mdpetrenko.market.services;

import com.github.mdpetrenko.market.dtos.OrderDetailsDto;
import com.github.mdpetrenko.market.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.model.Order;
import com.github.mdpetrenko.market.model.OrderItem;
import com.github.mdpetrenko.market.repositories.OrderRepository;
import com.github.mdpetrenko.market.services.interfaces.OrderService;
import com.github.mdpetrenko.market.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;

    @Transactional
    @Override
    public Order save(OrderDetailsDto orderDetails) {
        Order order = new Order(orderDetails);
        if (orderDetails.getOwnerId() != null) {
            order.setOwner(userService.findById(orderDetails.getOwnerId())
                    .orElseThrow(() -> new ResourceNotFoundException("User with id=" + orderDetails.getOwnerId() + " not found")));
        }
        order = orderRepository.save(order);
        order.setOrderItems(orderDetails.getCartItems().stream().map(OrderItem::new).collect(Collectors.toList()));
        return orderRepository.save(order);
    }
}
