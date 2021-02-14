package com.learn.simpleconsoleapp.seedworks;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;

import java.util.Arrays;

public class InspectRegisteredScopesPostProcessor implements BeanFactoryPostProcessor, Ordered {

    private int order = Ordered.LOWEST_PRECEDENCE;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Arrays.asList(beanFactory.getRegisteredScopeNames()).forEach(scopeName -> {
            var scope = beanFactory.getRegisteredScope(scopeName);
            System.out.println("Registered scope [name]:" + scopeName + " with [instance]:" + scope + ".\n");
        });
    }

    public void setOrder(int order){
        this.order = order;
    }

    @Override
    public int getOrder() {
        return order;
    }
}
