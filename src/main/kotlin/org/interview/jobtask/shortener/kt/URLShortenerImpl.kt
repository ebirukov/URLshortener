package org.interview.jobtask.shortener.org.interview.jobtask.shortener.kt

import org.interview.jobtask.shortener.kt.URLShortener
import org.interview.jobtask.shortener.kt.buildURL
import org.interview.jobtask.shortener.storage.kt.URLStorage

class URLShortenerImpl private constructor(private val urlStorage: URLStorage) : URLShortener {

    companion object {
        fun of(urlStorage: URLStorage) = URLShortenerImpl(urlStorage)
    }

    override fun shortening(originalUrl: String, keyword: String?)
            = buildURL(urlStorage.store(originalUrl, keyword))

    override fun retrieveOriginal(shortUrlPath: String) = urlStorage[shortUrlPath]


}