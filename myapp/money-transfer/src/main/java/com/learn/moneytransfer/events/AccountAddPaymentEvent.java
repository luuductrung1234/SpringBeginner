package com.learn.moneytransfer.events;

import com.learn.moneytransfer.models.Account;
import org.springframework.context.ApplicationEvent;

public class AccountAddPaymentEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the {@link Account} on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public AccountAddPaymentEvent(Object source) {
        super(source);
    }

    @Override
    public String toString() {
        if (!(this.source instanceof Account))
            throw new IllegalStateException(AccountAddPaymentEvent.class.getSimpleName() + " contains invalid source");

        var account = (Account) this.source;
        var paymentCount = account.getPaymentHistories().size();

        if (paymentCount < 1)
            throw new IllegalStateException(AccountAddPaymentEvent.class.getSimpleName() + " contains invalid source");

        return "[Event]: " +
                "\n\t[Added Payment]: " + account.getPaymentHistories().get(paymentCount - 1);
    }
}
