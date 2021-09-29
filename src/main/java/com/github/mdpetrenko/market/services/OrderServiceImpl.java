package com.github.mdpetrenko.market.services;

import com.github.mdpetrenko.market.dtos.OrderDetailsDto;
import com.github.mdpetrenko.market.dtos.OrderItemDto;
import com.github.mdpetrenko.market.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.model.Order;
import com.github.mdpetrenko.market.model.OrderItem;
import com.github.mdpetrenko.market.repositories.OrderRepository;
import com.github.mdpetrenko.market.services.interfaces.CartService;
import com.github.mdpetrenko.market.services.interfaces.OrderService;
import com.github.mdpetrenko.market.services.interfaces.ProductService;
import com.github.mdpetrenko.market.services.interfaces.UserService;
import com.github.mdpetrenko.market.model.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final UserService userService;
    private final CartService cartService;

    @Transactional
    @Override
    public void createOrder(OrderDetailsDto orderDetails, Principal principal, UUID uuid) {
        Order order = new Order(orderDetails);
        if (principal != null) {
            order.setUser(userService.findByUsername(principal.getName())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found. Name: " + principal.getName())));
        }
        Cart cart = cartService.getCartForCurrentUser(principal, uuid);
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
