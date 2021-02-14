package com.learn.moneytransfer.repositories;

import com.learn.moneytransfer.models.Account;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> getById(int id);
}
