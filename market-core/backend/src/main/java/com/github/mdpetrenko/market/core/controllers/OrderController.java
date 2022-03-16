package com.github.mdpetrenko.market.core.controllers;

import com.github.mdpetrenko.market.core.converters.OrderConverter;
import com.github.mdpetrenko.market.core.dtos.OrderDetailsDto;
import com.github.mdpetrenko.market.core.services.interfaces.OrderService;
import com.github.mdpetrenko.market.core.dtos.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveOrder(@RequestBody OrderDetailsDto orderDetails, @RequestHeader String username) {
        orderService.createOrder(orderDetails, username);
    }

    @GetMapping
    public ResponseEntity<?> listOrders(@RequestHeader String username) {
        return ResponseEntity.ok(orderService.findUserOrders(username)
                .stream().map(orderConverter::entityToDto).collect(Collectors.toList()));
    }

}
