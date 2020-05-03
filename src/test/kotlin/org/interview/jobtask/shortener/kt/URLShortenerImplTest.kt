package org.interview.jobtask.shortener.kt

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.interview.jobtask.shortener.org.interview.jobtask.shortener.kt.URLShortenerImpl
import org.interview.jobtask.shortener.storage.kt.URLStorage
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class URLShortenerImplTest {

    private val urlStorage = mock<URLStorage>()

    private val urlShortener = URLShortenerImpl.of(urlStorage)

    @Test
    fun shortening() {
        val url = "https://blog.mysite.com/cool-article"
        val keyword = "BEST-ARTICLE"
        whenever(urlStorage.store(url, keyword)).thenReturn(keyword)
        val output = urlShortener.shortening(url, keyword)
        assertEquals("https://short.en/BEST-ARTICLE", output)

        whenever(urlStorage.store(url, null)).thenReturn("Pq34r")
        assertEquals("https://short.en/Pq34r", urlShortener.shortening(url, null))
    }

    @Test
    fun retrieveOriginal() {
        var keyword = "BEST-ARTICLE"
        val url = "https://blog.mysite.com/cool-article"
        whenever(urlStorage[keyword]).thenReturn(url)
        assertEquals("https://blog.mysite.com/cool-article", urlShortener.retrieveOriginal(keyword))


        keyword = "Pq34r"
        whenever(urlStorage[keyword]).thenReturn("https://blog.mysite.com/another-article")
        assertEquals("https://blog.mysite.com/another-article", urlShortener.retrieveOriginal(keyword))
    }
}