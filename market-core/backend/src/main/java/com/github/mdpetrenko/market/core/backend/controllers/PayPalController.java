package com.github.mdpetrenko.market.core.backend.controllers;

import com.github.mdpetrenko.market.core.backend.services.PayPalService;
import com.github.mdpetrenko.market.core.backend.services.interfaces.OrderService;
import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.OrdersCaptureRequest;
import com.paypal.orders.OrdersCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/paypal")
@RequiredArgsConstructor
public class PayPalController {
    private final PayPalHttpClient payPalClient;
    private final PayPalService payPalService;
    private final OrderService orderService;

    @PostMapping("/create/{orderId}")
    public ResponseEntity<?> createOrder(@PathVariable Long orderId) throws IOException {
        OrdersCreateRequest request = new OrdersCreateRequest();
        request.prefer("return=representation");
        request.requestBody(payPalService.createOrderRequest(orderId));
        HttpResponse<Order> response = payPalClient.execute(request);
        return new ResponseEntity<>(response.result().id(), HttpStatus.valueOf(response.statusCode()));
    }

    @PostMapping("/capture/{payPalId}")
    public ResponseEntity<?> captureOrder(@PathVariable String payPalId) throws IOException {
        OrdersCaptureRequest request = new OrdersCaptureRequest(payPalId);
        request.requestBody(new OrderRequest());

        HttpResponse<Order> response = payPalClient.execute(request);
        Order payPalOrder = response.result();
        if ("COMPLETED".equals(payPalOrder.status())) {
            long orderId = Long.parseLong(payPalOrder.purchaseUnits().get(0).referenceId());
            orderService.changeOrderStatus(orderId, com.github.mdpetrenko.market.core.backend.entities.Order.OrderStatus.PAID);
            // Optional<com.geekbrains.spring.web.core.entities.Order> orderOptional = orderService.findById(orderId);
            return new ResponseEntity<>("Order completed!", HttpStatus.valueOf(response.statusCode()));
        }
        return new ResponseEntity<>(payPalOrder, HttpStatus.valueOf(response.statusCode()));
    }
}
