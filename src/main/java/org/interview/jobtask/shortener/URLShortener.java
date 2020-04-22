package org.interview.jobtask.shortener;

public interface URLShortener {

    String shortening(String originalUrl, String keyword) throws KeywordCollisionException;

    String shortening(String originalUrl) throws KeywordCollisionException;

    String retrieveOriginal(String shortUrlPath);
}
