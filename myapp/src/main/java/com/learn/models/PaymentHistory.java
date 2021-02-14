package com.learn.models;

public class PaymentHistory {
    private int AccountId;
    private double Amount;
    private PaymentMethod paymentMethod;
    private boolean verified;

    public PaymentHistory(int accountId, double amount, PaymentMethod paymentMethod, boolean verified) {
        AccountId = accountId;
        Amount = amount;
        this.paymentMethod = paymentMethod;
        this.verified = verified;
    }

    public int getAccountId() {
        return AccountId;
    }

    public double getAmount() {
        return Amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public boolean isVerified() {
        return verified;
    }

    @Override
    public String toString() {
        return "PaymentHistory { "
                + "[AccountId]: " + this.AccountId
                + ", [Amount]: " + this.Amount
                + ", [PaymentMethod]: " + this.paymentMethod.toString()
                + ", [Verified]:" + this.verified
                + " }";
    }
}
