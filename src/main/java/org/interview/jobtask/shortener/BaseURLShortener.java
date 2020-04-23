package org.interview.jobtask.shortener;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import static org.interview.jobtask.shortener.Application.*;

public class BaseURLShortener implements URLShortener {

    private final URLStorage storage;

    public BaseURLShortener(URLStorage urlStorage) {
        this.storage = Objects.requireNonNull(urlStorage);
    }

    @Override
    public String shortening(String originalUrl, String keyword) throws KeywordCollisionException {
        String id = storage.store(originalUrl, keyword);
        return buildURL(id);
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
