package org.interview.jobtask.shortener.org.interview.jobtask.shortener.storage.kt

import org.interview.jobtask.shortener.Application
import org.interview.jobtask.shortener.org.interview.jobtask.shortener.storage.identity.kt.IdentityGenerator
import org.interview.jobtask.shortener.storage.KeywordCollisionException
import org.interview.jobtask.shortener.storage.kt.URLStorage

class URLStorageImpl(private val identityGenerator: IdentityGenerator) : URLStorage {

    private val storage = hashMapOf<String, String>()

    companion object {
        fun instance() = URLStorageImpl(IdentityGenerator.of(Application.ALPHABET))
    }

    override fun store(originalUrl: String, keyword: String?): String {
        var id = keyword ?: identityGenerator.generate()
        if (id in storage)
            throw KeywordCollisionException("keyword $id")
        storage[id] = originalUrl
        return id
    }

    override fun retrieve(id: String) = storage[id]
}