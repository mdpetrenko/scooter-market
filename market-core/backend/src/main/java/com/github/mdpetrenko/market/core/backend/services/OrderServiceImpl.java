package com.github.mdpetrenko.market.core.backend.services;

import com.github.mdpetrenko.market.api.exceptions.ResourceNotFoundException;
import com.github.mdpetrenko.market.cart.dto.CartDto;
import com.github.mdpetrenko.market.cart.dto.CartItemDto;
import com.github.mdpetrenko.market.core.api.commons.OrderStatus;
import com.github.mdpetrenko.market.core.api.dto.OrderDetailsDto;
import com.github.mdpetrenko.market.core.backend.converters.ShippingAddressConverter;
import com.github.mdpetrenko.market.core.backend.entities.ShippingAddress;
import com.github.mdpetrenko.market.core.backend.entities.Order;
import com.github.mdpetrenko.market.core.backend.entities.OrderItem;
import com.github.mdpetrenko.market.core.backend.integrations.CartServiceIntegration;
import com.github.mdpetrenko.market.core.backend.repositories.ShippingAddressRepository;
import com.github.mdpetrenko.market.core.backend.repositories.OrderRepository;
import com.github.mdpetrenko.market.core.backend.services.interfaces.OrderService;
import com.github.mdpetrenko.market.core.backend.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
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
    private final ShippingAddressRepository shippingAddressRepository;
    private final ShippingAddressConverter shippingAddressConverter;

    @Override
    @Transactional
    public void createOrder(OrderDetailsDto orderDetails, String username) {
        Order order = new Order();
        CartDto cart = cartServiceIntegration.getCartForCurrentUser(username, orderDetails.getGuestCartUuid());
        Set<OrderItem> items = new HashSet<>();
        ShippingAddress shippingAddress = shippingAddressRepository.save(shippingAddressConverter.dtoToEntity(orderDetails.getShippingAddress()));
        order.setShippingAddress(shippingAddress);
        for (CartItemDto item : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setPrice(item.getPrice());
            orderItem.setProduct(productService.findById(item.getProductId()));
            orderItem.setPrice(item.getPrice());
            orderItem.setPricePerItem(item.getPricePerItem());
            orderItem.setQuantity(item.getQuantity());
            items.add(orderItem);
        }
        order.setItems(items);
        order.setOwnerName(orderDetails.getOwnerName());
        order.setOwnerEmail(orderDetails.getOwnerEmail());
        order.setOwnerPhone(orderDetails.getOwnerPhone());
        order.setPrice(cart.getTotalPrice());
        order.setStatus(OrderStatus.NEW);
        order.setUsername(username);
        orderRepository.save(order);
        cartServiceIntegration.clearCart(username, orderDetails.getGuestCartUuid());
    }

    @Override
    @Transactional
    public void changeOrderStatus(Long orderId, OrderStatus status) {
        Order order = findById(orderId);
        order.setStatus(status);
        orderRepository.save(order);
    }

    @Override
    public List<Order> findOrdersByUsername(String username) {
        return orderRepository.findAllByUsername(username);
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found. Id: " + id));
    }

}
