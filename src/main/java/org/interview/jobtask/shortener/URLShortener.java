package org.interview.jobtask.shortener;

import org.interview.jobtask.shortener.storage.KeywordCollisionException;

public interface URLShortener {

    String shortening(String originalUrl, String keyword) throws KeywordCollisionException;

    String retrieveOriginal(String shortUrlPath);
}
