package com.github.mdpetrenko.market.payment.backend.integrations;

import com.github.mdpetrenko.market.core.api.dto.OrderDto;

public class CoreServiceIntegration {

    public OrderDto findOrderById(Long id) {
        return new OrderDto();
    }

    public void changeOrderStatus(Long id) {}
}
