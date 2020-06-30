package strings

import org.junit.Test
import kotlin.test.assertEquals

class TestSuffixArray {

    @Test
    fun testSuffixArray() {
        val stringsToSuffixArray = mutableMapOf(
            "ababba" to listOf(6, 5, 0, 2, 4, 1, 3),
            "aaaa" to listOf(4, 3, 2, 1, 0),
            "ppppplppp" to listOf(9, 5, 8, 4, 7, 3, 6, 2, 1, 0),
            "nn" to listOf(2, 1, 0)
        )

        stringsToSuffixArray.forEach { (s, list) ->
            assertEquals(list, suffixArray(s))
        }
    }
}