package com.learn.moneytransfer.repositories;

import com.learn.moneytransfer.models.Account;
import com.learn.moneytransfer.models.AccountType;
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

    @Override
    public Optional<Account> getById(int id) {
        return accounts.stream().filter(a -> a.getId() == id).findFirst();
    }
}
