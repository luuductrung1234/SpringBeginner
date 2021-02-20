package com.learn.moneytransfer.commands;

public interface Command<TIn, TOut> {
    String getName();
    void setState(TIn state);
    TOut execute();
}
