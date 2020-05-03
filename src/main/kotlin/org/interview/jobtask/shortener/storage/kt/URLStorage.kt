package org.interview.jobtask.shortener.storage.kt

interface URLStorage {
    fun store(originalUrl: String, keyword: String?): String
    fun retrieve(id: String): String?

    operator fun get(id: String) = retrieve(id)
    operator fun set(keyword: String?, originalUrl: String) = store(originalUrl, keyword)
}