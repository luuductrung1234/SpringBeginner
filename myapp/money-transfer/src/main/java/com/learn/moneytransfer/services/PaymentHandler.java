package com.learn.moneytransfer.services;

import com.learn.moneytransfer.models.PaymentHistory;
import com.learn.moneytransfer.views.PaymentRequest;

public interface PaymentHandler {
    String getPaymentMethod();
    boolean verify(PaymentRequest paymentRequest);
    PaymentHistory pay(PaymentRequest paymentRequest);
}
