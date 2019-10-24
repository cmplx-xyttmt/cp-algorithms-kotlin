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

/**
 * Returns all permutations of the set {1, 2, ..., n}
 */
fun permutations(n: Int): List<List<Int>> {
    val permutations = mutableListOf<List<Int>>()
    val perm = mutableListOf<Int>()
    val chosen = MutableList(n + 1) { false }

    fun permutationHelper() {
        if (perm.size == n) permutations.add(perm.toList())
        else {
            (1..n).forEach {
                if (!chosen[it]) {
                    chosen[it] = true
                    perm.add(it)
                    permutationHelper()
                    perm.remove(it)
                    chosen[it] = false
                }
            }
        }
    }

    permutationHelper()

    return permutations.toList()
}