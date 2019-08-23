package strings

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
