package org.interview.jobtask.shortener.storage.kt

import org.interview.jobtask.shortener.org.interview.jobtask.shortener.storage.kt.URLStorageImpl
import org.interview.jobtask.shortener.storage.KeywordCollisionException
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class URLStorageImplTest {

    private val storage =  URLStorageImpl.instance()

    @Before
    fun before() {
        storage["BEST-ARTICLE"] = "https://blog.mysite.com/cool-article"
    }

    @Test(expected = KeywordCollisionException::class)
    fun store() {
        storage["BEST-ARTICLE"] = "https://blog.mysite.com/any"
    }

    @Test
    fun retrieve() {
        assertEquals("https://blog.mysite.com/cool-article", storage["BEST-ARTICLE"])
    }
}