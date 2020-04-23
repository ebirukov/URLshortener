package org.interview.jobtask.shortener.storage.identity;

import java.util.concurrent.ThreadLocalRandom;

public class IdentityGenerator {

    public final String alphabet;
    private static final int KEY_SIZE = 5;

    private ThreadLocalRandom random = ThreadLocalRandom.current();

    private IdentityGenerator(String alphabet) {
        this.alphabet = alphabet;
    }

    public static IdentityGenerator of(String alphabet) {
        return new IdentityGenerator(alphabet);
    }

    public String generate() {
        String key = "";
        for (int i = 0; i < KEY_SIZE; i++) {
            key += alphabet.charAt(random.nextInt(alphabet.length()));
        }
        return key;
    }

}
