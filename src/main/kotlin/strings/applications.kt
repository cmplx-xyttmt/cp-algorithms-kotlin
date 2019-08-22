package strings

/**
 * Application of hashing: Calculating number of different substrings of a string in O(n^2log n).
 */
fun computeUniqueSubstrings(string: String): Int {
    val p = 31
    val m = (1e9 + 9).toInt()
    val n = string.length
    val pPows = LongArray(n)
    pPows[0] = 1
    for (i in 1 until pPows.size) pPows[i] = (pPows[i - 1] * p) % m

    // prefixHash[i] is the hash of the prefix of string with i characters
    val prefixHash = LongArray(n + 1)
    for (i in 0 until n) prefixHash[i + 1] = (prefixHash[i] + (string[i] - 'a' + 1) * pPows[i]) % m

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
