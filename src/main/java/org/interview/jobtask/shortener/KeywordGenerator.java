package org.interview.jobtask.shortener;

import java.util.concurrent.ThreadLocalRandom;

public class KeywordGenerator {

    public static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int BASE = 5;

    private ThreadLocalRandom random = ThreadLocalRandom.current();

    private KeywordGenerator() { }

    public static KeywordGenerator instance() {
        return new KeywordGenerator();
    }

    public String generate() {
        String key = "";
        for (int i = 0; i < BASE; i++) {
            key += ALPHABET.charAt(random.nextInt(ALPHABET.length()));
        }
        return key;
    }

}
