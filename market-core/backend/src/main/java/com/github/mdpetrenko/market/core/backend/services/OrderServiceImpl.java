package com.github.mdpetrenko.market.core.backend.services;

import com.github.mdpetrenko.market.api.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.cart.dto.CartDto;
import com.github.mdpetrenko.market.cart.dto.CartItemDto;
import com.github.mdpetrenko.market.core.api.dto.OrderDetailsDto;
import com.github.mdpetrenko.market.core.api.exceptions.ProductNotFoundException;
import com.github.mdpetrenko.market.core.backend.entities.Order;
import com.github.mdpetrenko.market.core.backend.entities.OrderItem;
import com.github.mdpetrenko.market.core.backend.integrations.CartServiceIntegration;
import com.github.mdpetrenko.market.core.backend.repositories.OrderRepository;
import com.github.mdpetrenko.market.core.backend.services.interfaces.OrderService;
import com.github.mdpetrenko.market.core.backend.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/***
 * Service for create, find, delete orders
 * Interacts with market-cart service using CartServiceIntegration class
 */

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CartServiceIntegration cartServiceIntegration;

    @Override
    @Transactional
    public void createOrder(OrderDetailsDto orderDetails, String username) {
        Order order = new Order(orderDetails);
        if (username != null) {
            order.setUsername(username);
        }
        CartDto cart = cartServiceIntegration.getCartForCurrentUser(username, orderDetails.getGuestCartUuid());
        Set<OrderItem> items = new HashSet<>();
        for (CartItemDto item : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setPrice(item.getPrice());
            orderItem.setProduct(productService.findById(item.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found. Id: " + item.getProductId())));
            orderItem.setPrice(item.getPrice());
            orderItem.setPricePerItem(item.getPricePerItem());
            orderItem.setQuantity(item.getQuantity());
            items.add(orderItem);
        }
        order.setItems(items);
        order.setPrice(cart.getTotalPrice());
        orderRepository.save(order);
        cartServiceIntegration.clearCart(username, orderDetails.getGuestCartUuid());
    }

    @Override
    public void changeOrderStatus(Long orderId, Order.OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found. Id: " + orderId));
        order.setStatus(status);
        orderRepository.save(order);
    }

    @Override
    public List<Order> findOrdersByUsername(String username) {
        return orderRepository.findAllByUsername(username);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }
}
