package com.api.foodapp.service.impl;

import com.api.foodapp.dto.OrderRequest;
import com.api.foodapp.dto.OrderResponse;
import com.api.foodapp.entity.Order;
import com.api.foodapp.entity.Payment;
import com.api.foodapp.exception.PaymentException;
import com.api.foodapp.repository.OrderRepository;
import com.api.foodapp.repository.PaymentRepository;
import com.api.foodapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;
    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest)  {
        Order order = orderRequest.getOrder();
        order.setStatus("IN-PROGRESS");
        order.setOrderTrackingNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

        Payment payment = orderRequest.getPayment();

        if (!payment.getType().equals("DEBIT")){
            throw new PaymentException("Card type not supported");
        }
        payment.setOrderId(order.getId());
        paymentRepository.save(payment);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderTrackingNumber(order.getOrderTrackingNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("SUCCESS");
        return orderResponse;

    }
}
