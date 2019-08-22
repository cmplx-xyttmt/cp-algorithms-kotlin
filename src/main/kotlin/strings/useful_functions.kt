package strings

/**
 * For more information, check https://cp-algorithms.com/string/string-hashing.html
 *
 * Takes in a string and returns an integer that is the hash of the string.
 * This function is called a "polynomial rolling hash function".
 *
 * @param string: the string to be hashed.
 * @param p: a prime that is roughly equal to the number of characters in the input alphabet. Default value is 31 assuming an alphabet containing only lowercase english characters, need to update depending on size of alphabet.
 * @param m: modulus (probability of hash collision is 1/m), a good choice for this is a large prime number. Default value is 1e9 + 9.
 */
fun computeHash(string: String, p: Int = 31, m: Int = (1e9 + 9).toInt()): Long {
    var hashValue = 0L
    var pPow = 1L

    string.forEach { c ->
        hashValue = (hashValue + (c - 'a' + 1) * pPow) % m
        pPow = (pPow * p) % m
    }

    return hashValue
}
