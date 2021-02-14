package com.learn.commands;

public interface Command<TIn, TOut> {
    String getName();
    void setPaymentRequest(TIn paymentRequest);
    TOut execute();
}
