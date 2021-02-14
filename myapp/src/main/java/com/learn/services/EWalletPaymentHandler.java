package com.learn.services;

import com.learn.models.Account;
import com.learn.models.PaymentHistory;
import com.learn.repositories.AccountRepository;
import com.learn.views.PaymentRequest;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("EWallet")
public class EWalletPaymentHandler implements PaymentHandler, BeanNameAware {
    private String paymentMethod;

    private AccountRepository accountRepository;

    public EWalletPaymentHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public String getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public boolean verify(PaymentRequest paymentRequest) {
        Optional<Account> accountOpt = accountRepository.getById(paymentRequest.getAccountId());

        if (!accountOpt.isPresent())
            return false;

        Account account = accountOpt.get();

        switch (account.getType()) {
            case Business:
                return paymentRequest.getAmount() < 2_000_000;
            case Personal:
                return paymentRequest.getAmount() < 500_000;
            case MTO:
                return paymentRequest.getAmount() < 70_000_000;
        }

        return false;
    }

    @Override
    public PaymentHistory pay(PaymentRequest paymentRequest) {
        System.out.println("Received a payment request " + paymentRequest);
        System.out.println("Pay with " + paymentMethod);
        System.out.println("[Processing] call EWallet third-party. . . . . . . .");
        System.out.println(". . . . . . . .Completed!");

        Optional<Account> accountOpt = accountRepository.getById(paymentRequest.getAccountId());

        if (!accountOpt.isPresent())
            return null;

        Account account = accountOpt.get();

        PaymentHistory paymentHistory = new PaymentHistory(account.getId(), paymentRequest.getAmount(), paymentRequest.getPaymentMethod(), true);

        account.AddPayment(paymentHistory);

        Double earnedPoint = (paymentRequest.getAmount() / 100_000) * 0.001;
        account.earnBankPoint(earnedPoint);

        return paymentHistory;
    }

    @Override
    public void setBeanName(String name) {
        paymentMethod = name;
    }
}
