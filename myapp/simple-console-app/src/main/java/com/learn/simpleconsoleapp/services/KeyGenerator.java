package com.learn.simpleconsoleapp.services;

import com.learn.simpleconsoleapp.seedworks.advices.WeakKeyCheckAdvice;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Just simulate a cryptographic key generator, and randomly generate a weak key
 *
 * @see WeakKeyCheckAdvice
 */
@Service
public class KeyGenerator {
    public static final long WEAK_KEY = 0xFFFFFFF0000000L;
    public static final long STRONG_KEY = 0xACDF03F590AE56L;

    private Random rand = new Random();

    public long getKey() {
        System.out.println("Generating cryptographic key . . .");
        int x = rand.nextInt(3);
        if (x == 1) {
            return WEAK_KEY;
        }
        return STRONG_KEY;
    }
}
