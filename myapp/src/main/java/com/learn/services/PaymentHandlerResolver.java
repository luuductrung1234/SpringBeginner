package com.learn.services;

import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PaymentHandlerResolver {
    private final Set<PaymentHandler> paymentHandlers;

    public PaymentHandlerResolver(Set<PaymentHandler> paymentHandlers) {
        this.paymentHandlers = paymentHandlers;
    }

    public PaymentHandler getPaymentHandler(String paymentMethod) {
        return paymentHandlers.stream().filter(handler -> handler.getPaymentMethod().equalsIgnoreCase(paymentMethod)).findFirst().get();
    }
}
