package org.interview.jobtask.shortener;

import org.interview.jobtask.shortener.storage.KeywordCollisionException;
import org.interview.jobtask.shortener.storage.URLStorageImplV2;
import org.interview.jobtask.shortener.storage.identity.IdentityCodec;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class URLStorageImplV2Test {

    public static final int DECIMAL_EQ_BAAAA = 14776336;
    private IdentityCodec codec = mock(IdentityCodec.class);

    private URLStorageImplV2 storage = URLStorageImplV2.instance();

    @Before
    public void init() throws KeywordCollisionException {
        when(codec.decode(anyString())).thenReturn(Integer.MAX_VALUE);
        when(codec.decode(URLStorageImplV2.MIN5LETTER_KEYWORD)).thenReturn(DECIMAL_EQ_BAAAA);
        when(codec.encode(DECIMAL_EQ_BAAAA)).thenReturn("baaaa");
        storage.store("https://blog.mysite.com/other-article", null);
        storage.store("https://blog.mysite.com/cool-article", "BEST-ARTICLE");
    }

    @Test
    public void retrieve() {
        assertEquals("https://blog.mysite.com/cool-article", storage.retrieve("BEST-ARTICLE"));
        assertEquals("https://blog.mysite.com/other-article", storage.retrieve("baaaa"));
        assertNull(storage.retrieve("BEST-ARTICLE2"));
        assertNull(storage.retrieve("abcde"));
    }

    @Test
    public void store() throws KeywordCollisionException {
        when(codec.encode(DECIMAL_EQ_BAAAA + 2)).thenReturn("baaac");
        String id = storage.store("https://blog.mysite.com/other-article", null);
        assertEquals("baaac", id);
    }

}