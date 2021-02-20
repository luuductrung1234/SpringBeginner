package com.learn.moneytransfer.eventhandlers;

import com.learn.moneytransfer.events.AccountEarnBankPointEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AccountEarnBankPointEventHandler implements ApplicationListener<AccountEarnBankPointEvent> {
    @Override
    public void onApplicationEvent(AccountEarnBankPointEvent event) {
        System.out.println("\nReceived (" + AccountEarnBankPointEvent.class.getSimpleName() + "): " +
                "\n\t\t" + event + "\n");
    }
}
