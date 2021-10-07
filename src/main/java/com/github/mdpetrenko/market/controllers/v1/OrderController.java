package com.github.mdpetrenko.market.controllers.v1;

import com.github.mdpetrenko.market.dtos.OrderDetailsDto;
import com.github.mdpetrenko.market.dtos.OrderDto;
import com.github.mdpetrenko.market.services.interfaces.OrderService;
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

    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestBody OrderDetailsDto orderDetails, Principal principal) {
        orderService.createOrder(orderDetails, principal);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> listOrders(Principal principal) {
        return new ResponseEntity<>(orderService.findUserOrders(principal.getName())
                .stream().map(OrderDto::new).collect(Collectors.toList()), HttpStatus.OK);
    }

}
