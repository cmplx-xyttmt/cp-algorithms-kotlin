package strings

import kotlin.math.max

/**
 * Application of hashing: Calculating number of different substrings of a string in O(n^2log n).
 */
fun computeUniqueSubstrings(string: String): Int {
    val m = (1e9 + 9).toInt()
    val n = string.length
    val pPows = preComputePowers(n, m)

    val prefixHash = prefixHashes(string, pPows, m)

    var count = 0
    for (l in 1..n) {
        val substrings = mutableSetOf<Long>()
        for (i in 0..(n - l)) {
            var hash = (prefixHash[i + l] + m - prefixHash[i]) % m
            hash = (hash * pPows[n - i - l]) % m
            substrings.add(hash)
        }

        count += substrings.size
    }

    return count
}


/**
 * Rabin-Karp Algorithm for string matching:
 * (Reference: https://cp-algorithms.com/string/rabin-karp.html)
 *
 * Problem: Given 2 strings - a pattern s and a text t,
 * determine if the pattern appears in the text and if it does, enumerate all its occurrences in O(len(s) + len(t)).
 */
fun rabinKarp(s: String, t: String): List<Int> {
    val m = (1e9 + 9).toInt()
    val lenS = s.length
    val lenT = t.length

    val pPow = preComputePowers(max(lenS, lenT), m)
    val tPrefixHash = prefixHashes(t, pPow, m)
    val sHash = computeHash(s, m)

    val occurrences = mutableListOf<Int>()
    for (i in 0..(lenT - lenS)) {
        val substringHash = (tPrefixHash[i + lenS] + m - tPrefixHash[i]) % m
        if (substringHash == (sHash * pPow[i]) % m) occurrences.add(i)
    }

    return occurrences
}
