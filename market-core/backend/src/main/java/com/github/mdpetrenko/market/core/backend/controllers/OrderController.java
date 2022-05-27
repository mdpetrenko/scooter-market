package com.github.mdpetrenko.market.core.backend.controllers;

import com.github.mdpetrenko.market.core.api.commons.OrderStatus;
import com.github.mdpetrenko.market.core.api.dto.OrderDetailsDto;
import com.github.mdpetrenko.market.core.api.dto.OrderDto;
import com.github.mdpetrenko.market.core.api.exceptions.erors.CoreError;
import com.github.mdpetrenko.market.core.backend.converters.OrderConverter;
import com.github.mdpetrenko.market.core.backend.entities.Order;
import com.github.mdpetrenko.market.core.backend.services.interfaces.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Tag(name = "Orders", description = "Order methods")
public class OrderController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @Operation(summary = "Create new order in database",
            responses = {
                    @ApiResponse(
                            description = "Order created", responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "One or more products from order not found in database", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = CoreError.class))
                    )
            }
    )
    @PostMapping
    public void saveOrder(@RequestBody OrderDetailsDto orderDetails, @RequestHeader String username) {
        orderService.createOrder(orderDetails, username);
    }

    @Operation(summary = "List orders for current user",
            responses = {
                    @ApiResponse(
                            description = "Operation successful", responseCode = "200"
                    )
            }
    )
    @GetMapping
    public List<OrderDto> listOrders(@RequestHeader String username) {
        return orderService.findOrdersByUsername(username)
                .stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/{orderId}")
    public OrderDto findOrderById(@PathVariable Long orderId, @RequestHeader String username) {
        Order order = orderService.findById(orderId);
        return orderConverter.entityToDto(order);
    }

    @PutMapping("/{orderId}")
    public void updateOrderStatus(@PathVariable Long orderId, @RequestBody OrderStatus status, @RequestHeader String username) {
        orderService.changeOrderStatus(orderId, status);
    }

}
