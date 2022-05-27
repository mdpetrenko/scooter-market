package com.github.mdpetrenko.market.payment.backend.services;


import com.github.mdpetrenko.market.core.api.commons.OrderStatus;
import com.github.mdpetrenko.market.core.api.dto.OrderDto;
import com.github.mdpetrenko.market.core.api.dto.ShippingAddressDto;
import com.github.mdpetrenko.market.payment.backend.integrations.CoreServiceIntegration;
import com.paypal.orders.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final CoreServiceIntegration coreServiceIntegration;
    @Value("${paypal.currency}")
    private String currencyCode;
    @Value("${common.brand-name}")
    private String brandName;

    public OrderRequest createOrderRequest(String username, Long orderId) {
        OrderDto orderDto = coreServiceIntegration.findOrderById(username, orderId);
        ShippingAddressDto address = orderDto.getShippingAddress();
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
                .amountWithBreakdown(new AmountWithBreakdown().currencyCode(currencyCode).value(String.valueOf(orderDto.getPrice()))
                        .amountBreakdown(new AmountBreakdown().itemTotal(new Money().currencyCode(currencyCode).value(String.valueOf(orderDto.getPrice())))))
                .items(orderDto.getItems().stream()
                        .map(orderItem -> new Item()
                                .name(orderItem.getProductTitle())
                                .unitAmount(new Money().currencyCode(currencyCode).value(String.valueOf(orderItem.getPrice())))
                                .quantity(String.valueOf(orderItem.getQuantity())))
                        .collect(Collectors.toList()))
                .shippingDetail(new ShippingDetail().name(new Name().fullName(orderDto.getOwnerName()))
                        .addressPortable(new AddressPortable().addressLine1(address.getAddressLine1()).addressLine2(address.getAddressLine2())
                                .adminArea2(address.getAdminArea2()).adminArea1(address.getAdminArea1()).postalCode(address.getPostalCode()).countryCode(address.getCountryCode())));
        purchaseUnitRequests.add(purchaseUnitRequest);
        orderRequest.purchaseUnits(purchaseUnitRequests);
        return orderRequest;
    }

    public void changeOrderStatus(String username, Long orderId, OrderStatus status) {
        coreServiceIntegration.changeOrderStatus(username, orderId, OrderStatus.PAID);
    }
}
