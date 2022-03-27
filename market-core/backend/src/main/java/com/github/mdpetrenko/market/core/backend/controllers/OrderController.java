package com.github.mdpetrenko.market.core.backend.controllers;

import com.github.mdpetrenko.market.core.api.dto.OrderDetailsDto;
import com.github.mdpetrenko.market.core.api.dto.OrderDto;
import com.github.mdpetrenko.market.core.backend.converters.OrderConverter;
import com.github.mdpetrenko.market.core.backend.services.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveOrder(@RequestBody OrderDetailsDto orderDetails, @RequestHeader(required = false) String username) {
        orderService.createOrder(orderDetails, username);
    }

    @GetMapping
    public List<OrderDto> listOrders(@RequestHeader(required = false) String username) {
        return orderService.findUserOrders(username)
                .stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }

}
