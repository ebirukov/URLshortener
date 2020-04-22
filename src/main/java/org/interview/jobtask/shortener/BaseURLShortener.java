package org.interview.jobtask.shortener;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.interview.jobtask.shortener.Application.*;

public class BaseURLShortener implements URLShortener {

    private final URLStorage storage;

    private final KeywordGenerator keywordGenerator;

    public BaseURLShortener(KeywordGenerator keywordGenerator, URLStorage urlStorage) {
        this.storage = Objects.requireNonNull(urlStorage);
        this.keywordGenerator = Objects.requireNonNull(keywordGenerator);
    }

    @Override
    public String shortening(String originalUrl, String keyword) throws KeywordCollisionException {
        storage.store(originalUrl, keyword);
        return buildURL(keyword);
    }

    @Override
    public String shortening(String originalUrl) throws KeywordCollisionException {
        String keyword = keywordGenerator.generate();
        storage.store(originalUrl, keyword);
        return buildURL(keyword);
    }

    protected String buildURL(String keyword) {
        try {
            return new URL(HTTPS, SHORT_DOMAIN, "/" + keyword).toString();
        } catch (MalformedURLException e) {
            throw new RuntimeException("oops. Unexpected keyword " + keyword, e);
        }
    }

    @Override
    public String retrieveOriginal(String shortUrlPath) {
        return storage.retrieve(shortUrlPath);
    }
}
