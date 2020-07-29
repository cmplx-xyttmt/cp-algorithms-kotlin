package strings

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
    var p: IntArray // Ordering of the strings
    val c = IntArray(n) { -1 } // Equivalence classes

    // k = 0
    val charPosition = s.mapIndexed { index, ch -> Pair(ch, index) }.sortedBy { it.first }

    p = charPosition.map { it.second }.toIntArray()

    charPosition.forEachIndexed { index, pair ->
        when {
            index == 0 -> c[p[index]] = 0
            pair.first == charPosition[index - 1].first -> c[p[index]] = c[p[index - 1]]
            else -> c[p[index]] = c[p[index - 1]] + 1
        }
    }

    fun countSort(ordering: IntArray, classes: IntArray): IntArray {
        val newOrdering = IntArray(n)
        val counts = IntArray(n)
        classes.forEach { `class` -> counts[`class`]++ }

        val position = IntArray(n)
        for (i in 1 until n) position[i] = position[i - 1] + counts[i - 1]

        ordering.forEach { index ->
            val `class` = classes[index]
            newOrdering[position[`class`]] = index
            position[`class`]++
        }
        return newOrdering
    }

    var k = 0
    while (1 shl k < n) {
        // k -> k + 1

        for (i in p.indices) p[i] = (p[i] - (1 shl k) + n) % n

        p = countSort(p, c)
        val cNew = IntArray(n)
        cNew[p[0]] = 0
        for (i in 1 until n) {
            val prev = Pair(c[p[i - 1]], c[(p[i - 1] + (1 shl k)) % n])
            val curr = Pair(c[p[i]], c[(p[i] + (1 shl k)) % n])
            cNew[p[i]] = if (prev == curr) cNew[p[i - 1]] else cNew[p[i - 1]] + 1
        }
        cNew.forEachIndexed { index, `class` -> c[index] = `class` }
        k++
    }

    return p.toList()
}
