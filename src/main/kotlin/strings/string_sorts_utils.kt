package strings

/**
 * Sorts a list of integers in O(N + R) where N is the size of the list and R is the largest integer in the list.
 * This method of sorting is effective (read linear) if the integers to be sorted are within a constant factor of N.
 *
 * This implementation is based on Section 5.1 of Algorithms by Robert Sedgewick and Kevin Wayne.
 * @param items list of integers to be sorted. (With a few modifications, the method can be made general for any item
 *         with an integer key).
 * @param r largest integer in the list (can also be obtained by items.max()).
 */
fun keyIndexedCounting(items: List<Int>, r: Int): List<Int> {
    val count = MutableList(r + 2) { 0 }

    items.forEach { count[it + 1]++ }

    count.forEachIndexed { index, freq -> if (index < r) count[index + 1] += freq }

    val aux = MutableList(items.size) { 0 }

    items.forEach { aux[count[it]++] = it }

    return aux.toList()
}

/**
 * Sorts a list of strings based on their leading W characters.
 *
 * @param items list of strings to be sorted. (all strings are expected to be of the same length for this implementation)
 * @param W number of leading characters that determine the sort.
 */
fun lsdSort(items: MutableList<String>, W: Int): List<String> {
    val r = 256
    val aux = MutableList(items.size) { "" }
    (W - 1 downTo 0).forEach { d ->
        val count = MutableList(r + 1) { 0 }
        items.forEach { count[(it[d] + 1).toInt()]++ } // Compute frequency counts
        count.forEachIndexed { index, freq -> if (index < r) count[index + 1] += freq } // Transform counts to indices
        items.forEach { aux[count[it[d].toInt()]++] = it } // Distribute
        aux.forEachIndexed { index, s -> items[index] = s } // Copy back
    }
    return items
}

/**
 * Sorts a list of strings (doesn't need the strings to all be of the same length) using Most Significant Digit sort.
 *
 * @param items list of strings to be sorted.
 */
fun msdSort(items: MutableList<String>): List<String> {
    val aux = MutableList(items.size) { "" }
    val r = 256

    fun String.charAtMod(d: Int): Int {
        return if (d < length) this[d].toInt() else -1
    }

    /**
     * Used to terminate the msd sort when the array is small enough
     */
    fun insertionSort(items: MutableList<String>, lo: Int, hi: Int, d: Int) {
        (lo..hi).forEach { i ->
            var j = i
            while (j > lo && items[j].substring(d) < items[j - 1].substring(d)) {
                items[j] = items[j - 1].also { items[j - 1] = items[j] }
                j--
            }
        }
    }

    /**
     * Recursive helper for the MSD sort. Sorts from items[lo] to items[hi], starting at the [d] character.
     *
     * @param items list of strings being sorted
     * @param m cutoff for small sub-arrays
     */
    fun msdSortHelper(items: MutableList<String>, lo: Int, hi: Int, d: Int, m: Int = 0) {
        if (hi <= lo + m) {
            insertionSort(items, lo, hi, d)
            return
        }
        val count = MutableList(r + 2) { 0 }
        (lo..hi).forEach { count[items[it].charAtMod(d) + 2]++ }
        count.forEachIndexed { index, freq -> if (index < r) count[index + 1] += freq }
        (lo..hi).forEach { aux[count[items[it].charAtMod(d) + 1]++] = items[it] }
        (lo..hi).forEach { items[it] = aux[it - lo] }
        // Recursively sort for each character value
        (0 until r).forEach { msdSortHelper(items, lo + count[it], lo + count[it + 1] - 1, d + 1) }
    }

    msdSortHelper(items, 0, items.size - 1, 0, 15)
    return items
}
