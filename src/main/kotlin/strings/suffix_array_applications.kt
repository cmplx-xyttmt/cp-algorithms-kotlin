package strings

import kotlin.math.max
import kotlin.math.min

/**
 * Determine if a string s occurs in a string t using binary search on the suffix array
 *
 * @param t the text to search
 * @param s the text to find
 * @param tSuffixArray suffix array of t
 * @return whether s occurs in t or not
 */
fun substringSearch(t: String, s: String, tSuffixArray: List<Int>): Boolean {
    var start = 0
    var end = t.length
    var can = false
    while (start < end) {
        val mid = (start + end + 1) / 2
        val stringComp = t.substring(tSuffixArray[mid], min(t.length, tSuffixArray[mid] + s.length))
        val comp = s.compareTo(stringComp)
        if (comp == 0) {
            can = true
            break
        } else if (comp < 0) end = mid - 1
        else start = mid
    }
    return can
}

/**
 * The LCP(i, j) is the length of the largest common prefix for suffixes starting at i and j
 * This function returns an array lcp where lcp[ i ] = LCP(p[ i ], p[i + 1]) i.e the lcp of 2 consecutive suffixes in the suffix array.
 *
 * @param t the string
 * @param suffixArray suffix array of t
 * @return the lcp array as described above
 */
fun calcLCP(t: String, suffixArray: List<Int>): IntArray {
    val s = "${t}$"
    val n = s.length
    val lcp = IntArray(n - 1)
    val positions = IntArray(n)
    suffixArray.forEachIndexed { index, suff ->
        positions[suff] = index
    }

    var k = 0
    for (i in 0 until n - 1) {
        val position = positions[i]
        val j = suffixArray[position - 1]
        while (s[i + k] == s[j + k]) k++
        lcp[position - 1] = k
        k = max(k - 1, 0)
    }

    return lcp
}

/**
 * Calculating the number of different substrings of a string using a suffix array
 * @param t the input string
 * @return number of different substrings of t
 */
fun numberOfDifferentSubstrings(t: String): Long {
    val tSuffix = suffixArray(t)
    val lcp = calcLCP(t, tSuffix)
    var ans = 0L
    for (i in 1 until tSuffix.size) {
        ans += t.length - tSuffix[i] - lcp[i - 1]
    }

    return ans
}

/**
 * Gets the longest common substring of s and t
 *
 * @param s first string
 * @param t second string
 * @return longest common substring of s and t
 */
fun longestCommonSubstring(s: String, t: String): String {
    val combined = "$s%$t"
    val sRange = s.indices
    val tRange = (s.length + 1) until combined.length
    val suffixArray = suffixArray(combined)
    val lcp = calcLCP(combined, suffixArray)

    var maxCommon = 0
    var maxSuffix = -1
    for (i in 1 until suffixArray.size - 1) {
        val suffix = suffixArray[i]
        val nextSuffix = suffixArray[i + 1]
        if ((suffix in sRange && nextSuffix in tRange) || (suffix in tRange && nextSuffix in sRange)) {
            if (lcp[i] > maxCommon) {
                maxCommon = lcp[i]
                maxSuffix = suffix
            }
        }
    }

    return if (maxCommon == 0) "" else combined.substring(maxSuffix until (maxCommon + maxSuffix))
}
