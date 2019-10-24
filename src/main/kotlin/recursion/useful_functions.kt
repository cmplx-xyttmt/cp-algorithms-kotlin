package recursion

/**
 * Returns all subsets of the set {1, 2, ..., n}
 */
fun subsets(n: Int): List<List<Int>> {
    val subsets = mutableListOf<List<Int>>()
    val sub = mutableListOf<Int>()

    fun subsetHelper(k: Int) {
        if (k == n + 1) subsets.add(sub.toList())
        else {
            sub.add(k)
            subsetHelper(k + 1)
            sub.remove(k)
            subsetHelper(k + 1)
        }
    }

    subsetHelper(1)
    subsets.sortBy { it.size }
    return subsets.toList()
}