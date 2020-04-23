package org.interview.jobtask.shortener;

import org.junit.Test;

import static org.junit.Assert.*;

public class IdentityGeneratorTest {

    private IdentityGenerator identityGenerator = IdentityGenerator.of(Application.ALPHABET);

    @Test
    public void generate() {
        String key = identityGenerator.generate();
        assertNotNull(key);
        assertEquals(5, key.length());
        assertTrue(key.matches("^[a-zA-Z0-9]*$"));
    }
}