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

    @Test
    fun testSubstringSearch() {
        val t = "ynmxqxdftrqravcnpmikkxtezdtklbymaimnmxpyejtyuwicyfhmswdpxqydjhyzulumabcxblqdhhcepgbzrynieqdopmr"
        val strings = mapOf(
            "suknlb" to false,
            "ezdtklb" to true,
            "auksrkdpor" to false,
            "cpmxtp" to false,
            "censmxpyjhrkv" to false,
            "pcymldublngpqpxqrnev" to false,
            "qydjhyzulu" to true,
            "npmikkxtezdtklbymaim" to true,
            "maimnmxp" to true
        )

        val tSuffixArray = suffixArray(t)

        strings.forEach { (s, can) ->
            assertEquals(can, substringSearch(t, s, tSuffixArray))
        }
    }

    @Test
    fun testLCP() {
        val stringsToLCP = mutableMapOf(
            "ababba" to listOf(0, 1, 2, 0, 2, 1),
            "aaaa" to listOf(0, 1, 2, 3),
            "ppppplppp" to listOf(0, 0, 1, 1, 2, 2, 3, 3, 4),
            "nn" to listOf(0, 1),
            "jjqjjqujjq" to listOf(0, 3, 3, 1, 2, 2, 0, 1, 1, 0)
        )

        stringsToLCP.forEach { (s, lcp) ->
            assertEquals(lcp, calcLCP(s, suffixArray(s)).toList())
        }
    }

    @Test
    fun testNumberOfDifferentSubstrings() {
        val stringsToSubstrings = mutableMapOf(
            "ababba" to 15L,
            "mmuc" to 9L,
            "xmnnnuu" to 24L,
            "nnnn" to 4L,
            "xisiis" to 17L,
            "yymyfmyy" to 29L
        )

        stringsToSubstrings.forEach { (s, substrings) ->
            assertEquals(substrings, numberOfDifferentSubstrings(s))
        }
    }

    @Test
    fun testLongestCommonSubstring() {
        val stringsToCommon = mutableMapOf(
            Pair("bababb", "zabacabba") to "aba",
            Pair("qrdq", "rqqqrdqrqd") to "qrdq",
            Pair("hhhhhh", "hhhhhhh") to "hhhhhh",
            Pair("opopo", "ppppopopo") to "opopo"
        )

        stringsToCommon.forEach { (pair, common) ->
            val (s, t) = pair
            assertEquals(common, longestCommonSubstring(s, t))
        }
    }
}