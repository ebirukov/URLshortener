package org.interview.jobtask.shortener;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BaseURLShortenerTest {

    private KeywordGenerator keywordGenerator = mock(KeywordGenerator.class);

    private URLStorage storage = mock(URLStorage.class);

    private URLShortener urlShortener = new BaseURLShortener(keywordGenerator, storage);

    @Test
    public void shortening() throws KeywordCollisionException {
        String url = "https://blog.mysite.com/cool-article";
        String output = urlShortener.shortening(url, "BEST-ARTICLE");
        assertEquals("https://short.en/BEST-ARTICLE", output);

        when(keywordGenerator.generate()).thenReturn("Pq34r");
        assertEquals("https://short.en/Pq34r", urlShortener.shortening(url));
    }

    @Test
    public void retrieveOriginal() {
        String keyword = "BEST-ARTICLE";
        String url = "https://blog.mysite.com/cool-article";
        when(storage.retrieve(keyword)).thenReturn(url);
        assertEquals("https://blog.mysite.com/cool-article", urlShortener.retrieveOriginal(keyword));


        keyword = "Pq34r";
        when(storage.retrieve(keyword)).thenReturn("https://blog.mysite.com/another-article");
        assertEquals("https://blog.mysite.com/another-article", urlShortener.retrieveOriginal(keyword));
    }
}