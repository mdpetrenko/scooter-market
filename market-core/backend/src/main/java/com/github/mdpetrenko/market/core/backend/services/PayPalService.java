package com.github.mdpetrenko.market.core.backend.services;

import com.github.mdpetrenko.market.core.backend.entities.ShippingAddress;
import com.github.mdpetrenko.market.core.backend.services.interfaces.OrderService;
import com.paypal.orders.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PayPalService {
    private final OrderService orderService;
    @Value("${paypal.currency}")
    private String currencyCode;
    @Value("${common.brand-name}")
    private String brandName;

    @Transactional
    public OrderRequest createOrderRequest(Long orderId) {
        com.github.mdpetrenko.market.core.backend.entities.Order order = orderService.findById(orderId);
        ShippingAddress address = order.getShippingAddress();
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        ApplicationContext applicationContext = new ApplicationContext()
                .brandName(brandName)
                .landingPage("BILLING")
                .shippingPreference("SET_PROVIDED_ADDRESS");
        orderRequest.applicationContext(applicationContext);

        List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<>();
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                .referenceId(orderId.toString())
                .description("ScooterMarket Order")
                .amountWithBreakdown(new AmountWithBreakdown().currencyCode(currencyCode).value(String.valueOf(order.getPrice()))
                        .amountBreakdown(new AmountBreakdown().itemTotal(new Money().currencyCode(currencyCode).value(String.valueOf(order.getPrice())))))
                .items(order.getItems().stream()
                        .map(orderItem -> new Item()
                                .name(orderItem.getProduct().getTitle())
                                .unitAmount(new Money().currencyCode(currencyCode).value(String.valueOf(orderItem.getPrice())))
                                .quantity(String.valueOf(orderItem.getQuantity())))
                        .collect(Collectors.toList()))
                .shippingDetail(new ShippingDetail().name(new Name().fullName(order.getOwnerName()))
                        .addressPortable(new AddressPortable().addressLine1(address.getAddressLine1()).addressLine2(address.getAddressLine2())
                                .adminArea2(address.getAdminArea2()).adminArea1(address.getAdminArea1()).postalCode(address.getPostalCode()).countryCode(address.getCountryCode())));
        purchaseUnitRequests.add(purchaseUnitRequest);
        orderRequest.purchaseUnits(purchaseUnitRequests);
        return orderRequest;
    }
}
