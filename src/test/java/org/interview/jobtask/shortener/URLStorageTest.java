package org.interview.jobtask.shortener;

import org.junit.Test;

import static org.junit.Assert.*;

public class URLStorageTest {

    private URLStorage storage = URLStorage.instance();

    @Test(expected = KeywordCollisionException.class)
    public void store() throws KeywordCollisionException {
        storage.store("https://blog.mysite.com/cool-article", "BEST-ARTICLE");
        storage.store("https://blog.mysite.com/any", "BEST-ARTICLE");
    }

}