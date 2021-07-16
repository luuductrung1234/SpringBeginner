package com.learn.simpleconsoleapp.services;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;

@Component
public class MessageDigestFactoryBean implements FactoryBean<MessageDigest>, InitializingBean {
    public static final String DEFAULT_ALGORITHM_NAME = "MD5";

    @Value("#{environment.containsProperty('messagedigest.algorithme.name') ? environment.getProperty('messagedigest.algorithme.name') : T(com.learn.simpleconsoleapp.services.MessageDigestFactoryBean).DEFAULT_ALGORITHM_NAME}")
    private String algorithmName;

    private MessageDigest messageDigest;

    @Override
    public MessageDigest getObject() throws Exception {
        return messageDigest;
    }

    @Override
    public Class<MessageDigest> getObjectType() {
        return MessageDigest.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (!StringUtils.hasLength(algorithmName)) {
            algorithmName = DEFAULT_ALGORITHM_NAME;
        }
        messageDigest = MessageDigest.getInstance(algorithmName);
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }
}
