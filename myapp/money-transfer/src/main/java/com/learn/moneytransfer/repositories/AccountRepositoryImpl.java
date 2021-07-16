package com.learn.moneytransfer.repositories;

import com.learn.moneytransfer.models.Account;
import com.learn.moneytransfer.models.AccountType;
import com.learn.moneytransfer.publishers.EventPublisher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private List<Account> accounts = new ArrayList<Account>() {{
        add(new Account(1, "Tom", AccountType.Business));
        add(new Account(2, "Luu", AccountType.Personal));
        add(new Account(3, "Yen", AccountType.Business));
    }};

    private final EventPublisher eventPublisher;

    public AccountRepositoryImpl(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<Account> getById(int id) {
        return accounts.stream().filter(a -> a.getId() == id).findFirst();
    }

    /**
     * Mimic an EventRunner concept in infrastructure bean implementation
     */
    @Override
    public void save() {
        // publish events before perform save changes to database
        accounts.forEach(account -> {
            var events = account.getEvents();
            events.forEach(this.eventPublisher::publish);
        });

        // establish connection to database
        // perform a save change
    }
}
