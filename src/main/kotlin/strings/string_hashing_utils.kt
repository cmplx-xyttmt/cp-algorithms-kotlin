package strings

/**
 * For more information, check https://cp-algorithms.com/string/string-hashing.html
 *
 * Takes in a string and returns an integer that is the hash of the string.
 * This function is called a "polynomial rolling hash function".
 *
 * Some applications:
 *  - Used in the Rabin-Karp algorithm for string matching in O(n).
 *  - Calculating number of different substrings of a string in O(n^2log n).
 *  - Calculating number of palindromic substrings in a string.
 *
 * An easy trick for reducing the probability of collision is to use 2 different hashes (i.e use different p and/or m)
 *
 * @param string: the string to be hashed.
 * @param m: modulus (probability of hash collision is 1/m), a good choice for this is a large prime number. Default value is 1e9 + 9.
 */
fun computeHash(string: String, m: Int = (1e9 + 9).toInt()): Long {
    var hashValue = 0L
    val pPow = preComputePowers(string.length, m)

    string.forEachIndexed { i, c ->
        hashValue = (hashValue + (c - 'a' + 1) * pPow[i]) % m
    }

    return hashValue
}

/**
 * Returns an array of powers of p modulo m
 */
fun preComputePowers(size: Int, m: Int, p: Int = 31): LongArray {
    val pPow = LongArray(size)
    pPow[0] = 1
    for (i in 1 until pPow.size) pPow[i] = (pPow[i - 1] * p) % m

    return pPow
}

/**
 * Computes the hashes of the prefixes of the string.
 */
fun prefixHashes(string: String, pPows: LongArray, m: Int): LongArray {
    val n = string.length
    // prefixHash[i] is the hash of the prefix of string with i characters
    val prefixHash = LongArray(n + 1)
    for (i in 0 until n) prefixHash[i + 1] = (prefixHash[i] + (string[i] - 'a' + 1) * pPows[i]) % m

    return prefixHash
}
