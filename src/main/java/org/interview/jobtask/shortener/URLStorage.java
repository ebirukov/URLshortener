package org.interview.jobtask.shortener;

import java.util.HashMap;
import java.util.Map;

public class URLStorage {

    private final Map<String, String> storage;

    private URLStorage() {
        this.storage = new HashMap<>();
    }

    public static URLStorage instance() {
        return new URLStorage();
    }

    public void store(String originalUrl, String keyword) throws KeywordCollisionException {
        if (storage.containsKey(keyword)) throw new KeywordCollisionException("keyword " + keyword);
        storage.put(keyword, originalUrl);
    }

    public String retrieve(String keyword) {
        return storage.get(keyword);
    }
}
