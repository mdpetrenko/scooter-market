package com.github.mdpetrenko.market.services.interfaces;

import com.github.mdpetrenko.market.dtos.OrderDetailsDto;
import com.github.mdpetrenko.market.model.Order;

public interface OrderService {
    Order save(OrderDetailsDto orderDetails);
}
