package org.interview.jobtask.shortener.kt

import org.interview.jobtask.shortener.Application
import java.net.MalformedURLException
import java.net.URL

fun buildURL(keyword: String): String {
    return URL(Application.HTTPS, Application.SHORT_DOMAIN, "/$keyword").toString()
}

interface URLShortener {
    fun shortening(originalUrl: String, keyword: String?): String
    fun retrieveOriginal(shortUrlPath: String): String?
}