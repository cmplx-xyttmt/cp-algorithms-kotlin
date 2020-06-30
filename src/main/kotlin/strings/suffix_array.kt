package strings

/**
 * Creates the suffix array of the string s. For more info, checkout the EDU section on codeforces
 *
 * @param ss the string whose suffix array is to be created
 * @return the suffix array
 */
fun suffixArray(ss: String): List<Int> {
    val s = "$ss$"
    val n = s.length
    var p: List<Int> // Ordering of the strings
    val c = MutableList(n) { -1 } // Equivalence classes

    // k = 0
    val charPosition = s.mapIndexed { index, ch -> Pair(ch, index) }.sortedBy { it.first }

    p = charPosition.map { it.second }

    charPosition.forEachIndexed { index, pair ->
        when {
            index == 0 -> c[p[index]] = 0
            pair.first == charPosition[index - 1].first -> c[p[index]] = c[p[index - 1]]
            else -> c[p[index]] = c[p[index - 1]] + 1
        }
    }

    var k = 0
    while (1 shl k < n) {
        // k -> k + 1
        val eqPositionPair = MutableList(n) {
                index ->  Pair(ComparablePair(c[index], c[(index + (1 shl k)) % n]), index)
        }.sortedBy { it.first }

        p = eqPositionPair.map { it.second }

        eqPositionPair.forEachIndexed { index, pair ->
            when {
                index == 0 -> c[p[index]] = 0
                pair.first == eqPositionPair[index - 1].first -> c[p[index]] = c[p[index - 1]]
                else -> c[p[index]] = c[p[index - 1]] + 1
            }
        }

        k++
    }

    return p
}

data class ComparablePair(private val first: Int, private val second: Int): Comparable<ComparablePair> {

    override fun compareTo(other: ComparablePair): Int {
        if (first == other.first) return second - other.second
        return first - other.first
    }

    override fun toString(): String {
        return "Pair($first, $second)"
    }
}
