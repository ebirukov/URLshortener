package org.interview.jobtask.shortener;

import org.interview.jobtask.shortener.storage.KeywordCollisionException;
import org.interview.jobtask.shortener.storage.URLStorageImpl;
import org.junit.Test;

public class URLStorageImplTest {

    private URLStorageImpl storage = URLStorageImpl.instance();

    @Test(expected = KeywordCollisionException.class)
    public void store() throws KeywordCollisionException {
        storage.store("https://blog.mysite.com/cool-article", "BEST-ARTICLE");
        storage.store("https://blog.mysite.com/any", "BEST-ARTICLE");
    }

}