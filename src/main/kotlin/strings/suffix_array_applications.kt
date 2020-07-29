package strings

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