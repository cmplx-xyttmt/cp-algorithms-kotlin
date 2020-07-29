package strings

import kotlin.math.max

/**
 * Creates the suffix array of the string s. For more info, checkout the EDU section on codeforces
 * Note: A suffix array is a list of all suffixes of a string in lexicographic order.
 *
 * @param inputString the string whose suffix array is to be created
 * @return the suffix array
 */
fun suffixArray(inputString: String): List<Int> {
    val s = "$inputString$"
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
        val eqPositionPair = radixSort(MutableList(n) { index ->
            Pair(Pair(c[index], c[(index + (1 shl k)) % n]), index)
        })

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

/**
 * Implements radix sort on a list of pairs.
 *
 * @param pairs list to be sorted
 * @return a sorted version of the pairs list
 */
fun radixSort(pairs: List<Pair<Pair<Int, Int>, Int>>): List<Pair<Pair<Int, Int>, Int>> {
    val maxNum = pairs.map { pair -> max(pair.first.first, pair.first.second) }.max()!!

    fun sortPairBy(first: Boolean, pairsToSort: List<Pair<Pair<Int, Int>, Int>>): List<Pair<Pair<Int, Int>, Int>> {
        val buckets = MutableList(maxNum + 1) { mutableListOf<Pair<Pair<Int, Int>, Int>>() }

        pairsToSort.forEach { pair -> buckets[if (first) pair.first.first else pair.first.second].add(pair) }

        val sortedPairs = mutableListOf<Pair<Pair<Int, Int>, Int>>()
        buckets.forEach { bPairs -> sortedPairs.addAll(bPairs) }

        return sortedPairs
    }

    val sortedBySecond = sortPairBy(false, pairs)
    return sortPairBy(true, sortedBySecond)
}
