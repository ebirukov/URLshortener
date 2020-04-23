package org.interview.jobtask.shortener;

import org.junit.Test;

import static org.junit.Assert.*;

public class IdentityCodecTest {

    private IdentityCodec codec = IdentityCodec.of(Application.ALPHABET);

    @Test
    public void encode() {
        assertEquals(0, codec.decode("a"));
        assertEquals(1, codec.decode("b"));
        assertEquals(14776336, codec.decode("baaaa"));
    }

    @Test
    public void decode() {
        assertEquals("b", codec.encode(1));
        assertEquals("baaaa", codec.encode(14776336));
    }
}