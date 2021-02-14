package com.learn.moneytransfer.commands;

public interface Command<TIn, TOut> {
    String getName();
    void setPaymentRequest(TIn paymentRequest);
    TOut execute();
}
