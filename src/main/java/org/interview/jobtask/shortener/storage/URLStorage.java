package org.interview.jobtask.shortener.storage;

public interface URLStorage {
    String store(String originalUrl, String keyword) throws KeywordCollisionException;

    String retrieve(String id);
}
