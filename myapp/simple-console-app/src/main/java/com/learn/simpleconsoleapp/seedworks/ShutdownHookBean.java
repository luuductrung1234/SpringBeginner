package com.learn.simpleconsoleapp.seedworks;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Instruct Spring to register shutdown hook to underlying JVM Runtime.
 * The {@link AbstractApplicationContext#destroy()} or {@link AbstractApplicationContext#close()}  will be call automatically.
 * <p>
 * Notice:
 * With this bean, we don't have to actively call {@link AbstractApplicationContext#registerShutdownHook()}
 */
@Component
public class ShutdownHookBean implements ApplicationContextAware {
    /**
     * @param applicationContext
     * @throws BeansException
     * @Implement {@link ApplicationContextAware#setApplicationContext(ApplicationContext)}
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext instanceof AbstractApplicationContext) {
            ((AbstractApplicationContext) applicationContext).registerShutdownHook();
        }
    }
}
