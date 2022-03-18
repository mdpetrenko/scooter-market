package com.github.mdpetrenko.market.core.backend.services;

import com.github.mdpetrenko.market.api.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.cart.CartDto;
import com.github.mdpetrenko.market.cart.CartItemDto;
import com.github.mdpetrenko.market.core.api.dto.OrderDetailsDto;
import com.github.mdpetrenko.market.core.backend.entities.Order;
import com.github.mdpetrenko.market.core.backend.entities.OrderItem;
import com.github.mdpetrenko.market.core.backend.integrations.CartServiceIntegration;
import com.github.mdpetrenko.market.core.backend.repositories.OrderRepository;
import com.github.mdpetrenko.market.core.backend.services.interfaces.OrderService;
import com.github.mdpetrenko.market.core.backend.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CartServiceIntegration cartService;

    @Override
    public void createOrder(OrderDetailsDto orderDetails, String username) {
        Order order = new Order(orderDetails);
        if (username != null) {
            order.setUsername(username);
        }
        CartDto cart = cartService.getCartForCurrentUser(username, orderDetails.getGuestCartUuid());
        order.setPrice(cart.getTotalPrice());
        Set<OrderItem> items = new HashSet<>();
        for (CartItemDto item : cart.getItems()) {
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
        cartService.clearCart(username, orderDetails.getGuestCartUuid());
    }

    @Override
    public List<Order> findUserOrders(String username) {
        return orderRepository.findAllByUsername(username);
    }
}
