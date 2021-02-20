package com.learn.moneytransfer.eventhandlers;

import com.learn.moneytransfer.events.AccountAddPaymentEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AccountAddPaymentEventHandler implements ApplicationListener<AccountAddPaymentEvent> {
    @Override
    public void onApplicationEvent(AccountAddPaymentEvent event) {
        System.out.println("\nReceived (" + AccountAddPaymentEvent.class.getSimpleName() + "): " +
                "\n\t\t" + event + "\n");
    }
}
