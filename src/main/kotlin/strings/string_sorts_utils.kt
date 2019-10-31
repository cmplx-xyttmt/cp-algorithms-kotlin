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