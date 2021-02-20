package com.learn.moneytransfer.models;

import com.learn.moneytransfer.events.AccountAddPaymentEvent;
import com.learn.moneytransfer.events.AccountEarnBankPointEvent;
import com.learn.moneytransfer.events.AccountEarnEWalletPointEvent;

import java.util.ArrayList;
import java.util.List;

public class Account extends AggregateRoot{
    private int id;
    private String name;
    private AccountType type;
    private List<PaymentHistory> paymentHistories;
    private double memberBankPoint;
    private double memberEWalletPoint;

    public Account(int id, String name, AccountType type) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.paymentHistories = new ArrayList<>();
        this.memberBankPoint = 0;
        this.memberEWalletPoint = 0;
    }

    public void AddPayment(PaymentHistory paymentHistory) {
        this.addEvent(new AccountAddPaymentEvent(this));
        paymentHistories.add(paymentHistory);
    }

    public double earnBankPoint(double point) {
        this.addEvent(new AccountEarnBankPointEvent(this, point));
        return this.memberBankPoint += point;
    }

    public double earnEWalletPoint(double point) {
        this.addEvent(new AccountEarnEWalletPointEvent(this, point));
        return this.memberEWalletPoint += point;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AccountType getType() {
        return type;
    }

    public List<PaymentHistory> getPaymentHistories() {
        return paymentHistories;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Account { "
                + "[Id]:" + this.id
                + ", [Name]:" + this.name
                + ", [PaymentCount]:" + this.paymentHistories.stream().count()
                + ", [BankPoint]:" + this.memberBankPoint
                + ", [EWalletPoint]:" + this.memberEWalletPoint
                + " }\n");

        this.paymentHistories.forEach(p -> stringBuilder.append(p + "\n"));

        return stringBuilder.toString();
    }
}
