package org.interview.jobtask.shortener;

import java.util.HashMap;
import java.util.Map;

public class URLStorageImpl implements URLStorage {

    private final Map<String, String> storage;

    private final IdentityGenerator identityGenerator;

    private URLStorageImpl(IdentityGenerator identityGenerator) {
        this.identityGenerator = identityGenerator;
        this.storage = new HashMap<>();
    }

    public static URLStorageImpl instance() {
        return new URLStorageImpl(IdentityGenerator.of(Application.ALPHABET));
    }

    @Override
    public String store(String originalUrl, String keyword) throws KeywordCollisionException {
        if (keyword == null) {
            String id = identityGenerator.generate();
            storage.put(id, originalUrl);
            return id;
        }
        if (storage.containsKey(keyword)) throw new KeywordCollisionException("keyword " + keyword);
        storage.put(keyword, originalUrl);
        return keyword;
    }

    @Override
    public String retrieve(String id) {
        return storage.get(id);
    }
}
