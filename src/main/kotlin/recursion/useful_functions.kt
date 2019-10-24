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

/**
 * Returns the permutation following prevPerm in lexicographic order.
 * If there's no following permutation, returns an empty list.
 */
fun nextPermutation(prevPerm: List<Int>): List<Int> {
    var changeIndex = -1
    val nextPerm = prevPerm.toMutableList()
    nextPerm.forEachIndexed { index, num ->
        if (index < nextPerm.size - 1 && nextPerm[index + 1] > num) changeIndex = index
    }

    if (changeIndex == -1) return listOf()
    var nextGreaterIndex = changeIndex
    ((changeIndex + 1) until prevPerm.size).forEach {
        if (prevPerm[it] > prevPerm[changeIndex]) nextGreaterIndex = it
    }

    if (changeIndex == nextGreaterIndex) return listOf()

    nextPerm[changeIndex] = nextPerm[nextGreaterIndex].also { nextPerm[nextGreaterIndex] = nextPerm[changeIndex] }

    var last = prevPerm.size - 1
    changeIndex++
    while (changeIndex < last) {
        nextPerm[changeIndex] = nextPerm[last].also { nextPerm[last] = nextPerm[changeIndex] }
        changeIndex++
        last--
    }

    return nextPerm.toList()
}
