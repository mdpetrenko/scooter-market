package com.github.mdpetrenko.market.core.services;

import com.github.mdpetrenko.market.api.dto.CartDto;
import com.github.mdpetrenko.market.api.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.core.dtos.OrderDetailsDto;
import com.github.mdpetrenko.market.api.dto.OrderItemDto;
import com.github.mdpetrenko.market.core.entities.Order;
import com.github.mdpetrenko.market.core.entities.OrderItem;
import com.github.mdpetrenko.market.core.repositories.OrderRepository;
import com.github.mdpetrenko.market.core.services.interfaces.OrderService;
import com.github.mdpetrenko.market.core.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final RestTemplate restTemplate;

    @Override
    public void createOrder(OrderDetailsDto orderDetails, String username) {
        Order order = new Order(orderDetails);
        if (username != null) {
            order.setUsername(username);
        }
        Cart cart = cartService.getCartForCurrentUser(username, orderDetails.getGuestCartUuid());
        order.setPrice(cart.getTotalPrice());
        Set<OrderItem> items = new HashSet<>();
        for (OrderItemDto item : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setPrice(item.getPrice());
            orderItem.setProduct(productService.findById(item.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found. Id: " + item.getProductId())));
            orderItem.setPrice(item.getPrice());
            orderItem.setPricePerItem(item.getPricePerItem());
            orderItem.setQuantity(item.getQuantity());
            items.add(orderItem);
        }
        order.setItems(items);
        orderRepository.save(order);
        cartService.clearCart(cart);
    }

    @Override
    public List<Order> findUserOrders(String username) {
        return orderRepository.findAllByUsername(username);
    }
}
