package org.interview.jobtask.shortener;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BaseURLShortenerTest {

    private URLStorageImpl storage = mock(URLStorageImpl.class);

    private URLShortener urlShortener = new BaseURLShortener(storage);

    @Test
    public void shortening() throws KeywordCollisionException {
        String url = "https://blog.mysite.com/cool-article";
        String keyword = "BEST-ARTICLE";
        when(storage.store(url, keyword)).thenReturn(keyword);
        String output = urlShortener.shortening(url, keyword);
        assertEquals("https://short.en/BEST-ARTICLE", output);

        when(storage.store(url, null)).thenReturn("Pq34r");
        assertEquals("https://short.en/Pq34r", urlShortener.shortening(url, null));
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