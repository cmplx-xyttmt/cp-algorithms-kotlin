package math

import java.math.BigInteger

fun Long.pow(exp: Int) = BigInteger.valueOf(this).pow(exp).toLong()

/**
 * Determines if the number n is prime
 */
fun isPrime(n: Long): Boolean {
    if (n < 2) return false
    var x = 2
    while (x * x <= n) {
        if (n % x == 0L) return false
        x++
    }
    return true
}

/**
 * Returns the prime factorisation of n
 */
fun factorise(nn: Long): List<Long> {
    val factors = mutableListOf<Long>()
    var x = 2L
    var n = nn
    while (x * x <= n) {
        while (n % x == 0L) {
            factors.add(x)
            n /= x
        }
        x++
    }
    if (n > 1) factors.add(n)
    return factors
}

/**
 * Returns prime factors of n in a map where key is the factor,
 * and value is the exponent of the factor in the prime factorisation of n.
 */
fun primeFactors(n: Long): Map<Long, Int> {
    val factors = factorise(n)
    val occurrences = mutableMapOf<Long, Int>()
    factors.forEach { factor -> occurrences[factor] = occurrences[factor]?.plus(1) ?: 1 }
    return occurrences
}

/**
 * Sieve of eratosthenes. Builds an array in which we can efficiently check if a number between 2..n is prime.
 */
fun sieve(n: Int): List<Boolean> {
    val isPrime = MutableList(n + 1) { true }
    isPrime[0] = false
    isPrime[1] = false
    for (x in 2..n) {
        if (!isPrime[x]) continue
        for (u in (2 * x)..n step x) isPrime[u] = false
    }

    return isPrime
}

/**
 * Uses Euclid's algorithm to find the greatest common divisor of a and b.
 */
fun gcd(a: Long, b: Long): Long {
    if (b == 0L) return a
    return gcd(b, a % b)
}

/**
 * Finds the lcm of a and b
 */
fun lcm(a: Long, b: Long): Long {
    return a * b / gcd(a, b)
}

/**
 * Euler's totient function: Returns number of coprime numbers to n between 1 and n.
 */
fun eulerTotient(n: Long): Long {
    val factors = primeFactors(n)
    var res = 1L
    factors.forEach { (prime, occurr) -> res *= (prime.pow(occurr - 1) * (prime - 1)) }
    return res
}

/**
 * Number of factors of n
 */
fun numOfFactors(n: Long): Int {
    return primeFactors(n).values.fold(1) { acc, exp -> acc * (exp + 1) }
}

/**
 * Sum of factors of n
 */
fun sumOfFactors(n: Long): Long {
    val factorExpPairs = primeFactors(n).map { (prime, exp) -> Pair(prime, exp) }
    return factorExpPairs.fold(1L) { acc, pair ->
        acc * (pair.first.pow(pair.second + 1) - 1) / (pair.first - 1)
    }
}

/**
 * Determines if n is a perfect number
 */
fun isPerfectNumber(n: Long): Boolean {
    return n == sumOfFactors(n) - n
}

/**
 * Using the Extended Euclid's Algorithm to find integers x and y for which ax + by = gcd(a, b)
 * returns the list [x, y, g]
 */
fun extendedGcd(a: Int, b: Int): List<Int> {
    return if (b == 0) listOf(1, 0, a)
    else {
        val (x, y, g) = extendedGcd(b, a % b)
        listOf(y, x - (a / b) * y, g)
    }
}

/**
 * Modular exponentiation: calculates x ^ n mod m in O(log n) time
 */
fun modPow(x: Long, n: Int, m: Long): Long {
    if (n == 0) return 1 % m
    var u = modPow(x, n / 2, m)
    u = (u * u) % m
    if (n % 2 == 1) u = (u * (x % m)) % m
    return u
}

/**
 * Modular inverse: Uses x ^ (phi(m) - 1) to calculate the modular inverse of x mod m.
 * (Remember, the modular inverse only exists when x and m are co-prime)
 * If m is prime, eulerTotient(m).toInt() - 1 can be replaced by m - 2.
 */
fun modInverse(x: Long, m: Long): Long {
    return modPow(x, eulerTotient(m).toInt() - 1, m)
}

// TODO: Read section on solving equations and chinese remainder theorem.
//  TODO: Also read this blog (https://codeforces.com/blog/entry/61290) for more info and problems on the same.
