package com.learn.simpleconsoleapp.services;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
public class MessageDigester {
    private final MessageDigest messageDigest;

    public MessageDigester(MessageDigest messageDigest) {
        this.messageDigest = messageDigest;
    }

    public void digest(String msg) {
        System.out.println("Obtain " + MessageDigest.class.getSimpleName() + messageDigest);
        System.out.println("Digesting . . .");
        digest(msg, messageDigest);
    }

    private void digest(String msg, MessageDigest digest) {
        System.out.println("Using algorithm: " + digest.getAlgorithm());
        digest.reset();
        byte[] bytes = msg.getBytes();
        byte[] out = digest.digest(bytes);
        System.out.println("result: " + out + "\n");
    }
}
