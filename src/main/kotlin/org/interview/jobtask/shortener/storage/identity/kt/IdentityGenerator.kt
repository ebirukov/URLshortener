package org.interview.jobtask.shortener.org.interview.jobtask.shortener.storage.identity.kt

import java.util.concurrent.ThreadLocalRandom

class IdentityGenerator private constructor(private val alphabet: String) {

    private val random = ThreadLocalRandom.current()

    companion object {
        fun of(alphabet: String) = IdentityGenerator(alphabet)
        private const val KEY_SIZE = 5
    }

    fun generate() = buildString {
        for (i in 1..KEY_SIZE) {
            append(alphabet[random.nextInt(alphabet.length)])
        }
    }
}