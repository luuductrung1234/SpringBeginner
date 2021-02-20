package com.learn.moneytransfer.events;

import com.learn.moneytransfer.models.Account;
import org.springframework.context.ApplicationEvent;

public class AccountEarnEWalletPointEvent extends ApplicationEvent {
    private final double earnedPoint;

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public AccountEarnEWalletPointEvent(Object source, double earnedPoint) {
        super(source);
        this.earnedPoint = earnedPoint;
    }

    public double getEarnedPoint() {
        return earnedPoint;
    }

    @Override
    public String toString() {
        if (!(this.source instanceof Account))
            throw new IllegalStateException(AccountAddPaymentEvent.class.getSimpleName() + " contains invalid source");
        return "[Event]: " +
                "\n\t[Earned EWallet Point]: " + this.earnedPoint;
    }
}
