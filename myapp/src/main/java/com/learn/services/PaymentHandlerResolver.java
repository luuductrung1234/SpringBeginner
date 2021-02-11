package com.learn.services;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class PaymentHandlerResolver implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public PaymentHandler getPaymentHandler(String paymentMethod){
        return this.applicationContext.getBean(paymentMethod, PaymentHandler.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
