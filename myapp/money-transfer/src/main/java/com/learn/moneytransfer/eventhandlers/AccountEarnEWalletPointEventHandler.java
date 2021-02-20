package com.learn.moneytransfer.eventhandlers;

import com.learn.moneytransfer.events.AccountEarnEWalletPointEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AccountEarnEWalletPointEventHandler implements ApplicationListener<AccountEarnEWalletPointEvent> {
    @Override
    public void onApplicationEvent(AccountEarnEWalletPointEvent event) {
        System.out.println("\nReceived (" + AccountEarnEWalletPointEvent.class.getSimpleName() + "):" +
                "\n\t\t" + event + "\n");
    }
}
