package com.api.foodapp.dto;

import com.api.foodapp.entity.Order;
import com.api.foodapp.entity.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private Order order;
    private Payment payment;
}
