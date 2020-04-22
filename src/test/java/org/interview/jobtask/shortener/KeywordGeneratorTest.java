package org.interview.jobtask.shortener;

import org.junit.Test;

import static org.junit.Assert.*;

public class KeywordGeneratorTest {

    private KeywordGenerator keywordGenerator = KeywordGenerator.instance();

    @Test
    public void generate() {
        String key = keywordGenerator.generate();
        assertNotNull(key);
        assertEquals(5, key.length());
        assertTrue(key.matches("^[a-zA-Z0-9]*$"));
    }
}