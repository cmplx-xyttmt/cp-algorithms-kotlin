package math

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TestNumberTheory {

    @Test
    fun testIsPrime() {
        assertTrue(isPrime(2))
        assertTrue(isPrime(3))
        assertTrue(isPrime(17))
        assertTrue(isPrime(199))
        assertTrue(isPrime(2459))
        assertTrue(isPrime(1000004233))

        assertFalse(isPrime(78))
        assertFalse(isPrime(1000004233 + 1))
        assertFalse(isPrime(18))
        assertFalse(isPrime(378173138))
        assertFalse(isPrime(3 * 5 * 7 * 11 * 17))
    }

    @Test
    fun testFactorise() {
        var factors = factorise(12)
        assertEquals(listOf(2L, 2, 3), factors)

        factors = factorise(18)
        assertEquals(listOf(2L, 3, 3), factors)

        factors = factorise(2 * 2 * 3 * 5 * 5 * 5 * 19 * 29 * 31)
        assertEquals(listOf(2L, 2, 3, 5, 5, 5, 19, 29, 31), factors)
    }

    @Test
    fun testSieve() {
        val sieveList = sieve(1000000)
        assertEquals(
            listOf(false, false, true, true, false, true, false, true, false, false, false),
            sieveList.subList(0, 11)
        )

        assertFalse(sieveList[1000000])

        assertTrue(sieveList[2459])
    }

    @Test
    fun testGcd() {
        assertEquals(2, gcd(2, 4))
        assertEquals(1, gcd(19, 17))
        assertEquals(1000, gcd(0, 1000))
    }

    @Test
    fun testLcm() {
        assertEquals(6, lcm(2, 3))
        assertEquals(17, lcm(1, 17))
        assertEquals(6, lcm(6, 3))
        assertEquals(36, lcm(18, 12))
    }

    @Test
    fun testEulerTotient() {
        assertEquals(1, eulerTotient(1))
        assertEquals(1, eulerTotient(2))
        assertEquals(2, eulerTotient(3))
        assertEquals(4, eulerTotient(12))
        assertEquals(16, eulerTotient(32))
        assertEquals(448, eulerTotient(493))
        assertEquals(184, eulerTotient(376))
    }

    @Test
    fun testNumberOfFactors() {
        assertEquals(1, numOfFactors(1))
        assertEquals(2, numOfFactors(2))
        assertEquals(2, numOfFactors(3))
        assertEquals(6, numOfFactors(12))
        assertEquals(8, numOfFactors(24))
        assertEquals(2, numOfFactors(89))
        assertEquals(6, numOfFactors(99))
        assertEquals(3, numOfFactors(25))
        assertEquals(5, numOfFactors(16))
    }

    @Test
    fun testIsPerfectNumber() {
        assertTrue(isPerfectNumber(6))
        assertTrue(isPerfectNumber(28))
        assertTrue(isPerfectNumber(496))
        assertTrue(isPerfectNumber(8128))
        assertTrue(isPerfectNumber(33550336))
        assertTrue(isPerfectNumber(8589869056L))

        assertFalse(isPerfectNumber(26))
        assertFalse(isPerfectNumber(4))
        assertFalse(isPerfectNumber(17))
        assertFalse(isPerfectNumber(75))
        assertFalse(isPerfectNumber(12))
    }

    @Test
    fun testExtendedGcd() {
        val a = listOf(30, 3, 2, 45, 67, 89, 67, 48490397)
        val b = listOf(12, 2, 5, 67, 78, 90, 10, 813575028)

        for (i in a.indices) {
            val (x, y, g) = extendedGcd(a[i], b[i])
            assertEquals(x * a[i] + y * b[i], g)
        }
    }

    @Test
    fun testModPow() {
        val m = 10L.pow(9) + 7
        val x = listOf(1L, 2, 3, 7, 9, 10)
        val n = listOf(8, 7, 18, 19, 10, 7)

        for (i in x.indices) {
            assertEquals(modPow(x[i], n[i], m), x[i].pow(n[i]) % m)
        }
    }

    @Test
    fun testModInverse() {
        val x = listOf(1L, 2, 3, 17, 19, 20, 17, 10008, 999999)
        val m = listOf(17L, 17, 2, 5, 89, 19, 29, 71, 10L.pow(9) + 7)

        for (i in x.indices) {
            assertEquals((x[i] * modInverse(x[i], m[i])) % m[i], 1)
        }
    }
}
