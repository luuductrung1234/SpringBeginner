package com.learn.services;

import com.learn.models.PaymentHistory;
import com.learn.views.PaymentRequest;

public interface PaymentHandler {
    String getPaymentMethod();
    boolean verify(PaymentRequest paymentRequest);
    PaymentHistory pay(PaymentRequest paymentRequest);
}
