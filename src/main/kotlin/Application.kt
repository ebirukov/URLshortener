package org.interview.jobtask.shortener

import org.interview.jobtask.shortener.kt.URLShortener
import org.interview.jobtask.shortener.org.interview.jobtask.shortener.kt.URLShortenerImpl
import org.interview.jobtask.shortener.org.interview.jobtask.shortener.storage.kt.URLStorageImpl
import org.interview.jobtask.shortener.storage.KeywordCollisionException
import java.net.MalformedURLException
import java.net.URL
import java.util.*

object Application {
    private val scanner = Scanner(System.`in`)
    const val SHORT_DOMAIN = "short.en"
    const val HTTPS = "https"
    const val ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"

    @JvmStatic
    fun main(args: Array<String>) {
        val shortener = createURLShortener(args)
        while (true) {
            println("---------------------------")
            println("input original url to shortening, or short url to retrieve original")
            println("---------------------------")
            val inputUrl = readURL()
            if (SHORT_DOMAIN.equals(inputUrl.host, ignoreCase = true)) {
                val originalUrl = shortener.retrieveOriginal(inputUrl.path.substring(1))
                val message = originalUrl ?: "not found"
                println("original URL $message")
            } else {
                val keyword = readKeyword()
                val originalUrl = inputUrl.toString()
                var shortenUrl: String
                try {
                    shortenUrl = shortener.shortening(originalUrl, keyword)
                    println("shorten URL: $shortenUrl")
                } catch (e: KeywordCollisionException) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun createURLShortener(args: Array<String>): URLShortener {
        val storage = if (args.size == 1
                && "v2".equals(args[0], ignoreCase = true)) args[0] else "v1"
        println("start with storage $storage")
        return if ("v2".equals(storage, ignoreCase = true)) {
            //BaseURLShortenerImpl(URLStorageImplV2.instance())
            URLShortenerImpl.of(URLStorageImpl.instance())
        } else {
            URLShortenerImpl.of(URLStorageImpl.instance())
        }
    }

    private fun readKeyword(): String? {
        while (true) {
            print("input keyword (optional): ")
            val keyword = scanner.nextLine()
            if (keyword.isBlank()) {
                return null
            } else if (keyword.length > 20) {
                System.err.println("keyword should be at max 20 symbols. Try again")
                continue
            }
            return keyword
        }
    }

    private fun readURL(): URL {
        while (true) {
            print("input url: ")
            val urlString = scanner.nextLine()
            val url = buildURL(urlString)
            if (url != null) {
                return url
            }
            System.err.println("wrong url. Try again")
        }
    }

    private fun buildURL(urlString: String): URL? {
        return try {
            val url = URL(urlString)
            if (url.host.isBlank()
                    || url.protocol.isBlank()
                    || !HTTPS.equals(url.protocol, ignoreCase = true)
                    || url.path.isBlank()) {
                null
            } else url
        } catch (e: MalformedURLException) {
            null
        }
    }

}