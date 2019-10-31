package strings

import org.junit.Test
import kotlin.test.assertEquals

class TestStringHashing {

    @Test fun testComputeHash() {
        assertEquals(614254, computeHash("test"))
        assertEquals(952913702, computeHash("ahdhaweqweqdadawdw"))
    }

    @Test fun testCountUniqueSubstrings() {
        assertEquals(9, computeUniqueSubstrings("test"))
        assertEquals(23, computeUniqueSubstrings("aaadawd"))
    }

    @Test fun testRabinKarp() {
        assertEquals(listOf(0, 5, 10), rabinKarp("ab", "abdadabdwdab"))
        assertEquals(listOf(), rabinKarp("adashda", "adashd"))
        assertEquals(listOf(0), rabinKarp("adashda", "adashda"))
    }
}
