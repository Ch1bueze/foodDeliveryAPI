package com.api.foodapp.service;

import com.api.foodapp.dto.OrderRequest;
import com.api.foodapp.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
}
