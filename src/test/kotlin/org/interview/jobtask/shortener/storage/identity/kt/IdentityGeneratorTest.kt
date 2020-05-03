package org.interview.jobtask.shortener.storage.identity.kt

import org.interview.jobtask.shortener.org.interview.jobtask.shortener.storage.identity.kt.IdentityGenerator
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class IdentityGeneratorTest {

    private val generator: IdentityGenerator = IdentityGenerator.of("123456789")

    @Test
    fun generate() {
        val key = generator.generate()
        assertEquals(5, key.length)
        assertTrue( key.matches("[0-9]*".toRegex()) )
    }

}