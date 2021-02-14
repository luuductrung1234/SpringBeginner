package com.learn.repositories;

import com.learn.models.Account;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> getById(int id);
}
