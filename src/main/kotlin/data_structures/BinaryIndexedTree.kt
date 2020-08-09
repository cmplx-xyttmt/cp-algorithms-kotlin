package data_structures

/**
 * A BIT (or Fenwick Tree) can be seen as a dynamic variant of a prefix sum array.
 * Supports two O(log n) time operations on an array - processing a range sum query and updating a value.
 *
 * Each index in the tree stores sum(k - p(k) + 1, k) where p(k) denotes the largest power of 2 that divides k.
 * Note that p(k) = k & -k
 *
 * @param array the array from which to create the tree; the array has to be 1-indexed
 */
class BinaryIndexedTree(array: IntArray) {
    private val tree = IntArray(array.size)
    private val n = array.size - 1 // last index

    init {
        array.forEachIndexed { index, num ->
            if (index >= 1) add(index, num)
        }
    }

    /**
     * Calculates sum(1, kk)
     * @param kk index up-to which to sum
     */
    private fun sum(kk: Int): Int {
        var k = kk
        var s = 0
        while (k >= 1) {
            s += tree[k]
            k -= k and -k
        }
        return s
    }

    /**
     * Adds the value x to the number in position kk
     * @param kk index to which to add
     * @param x the number to add
     */
    fun add(kk: Int, x: Int) {
        var k = kk
        while (k <= n) {
            tree[k] += x
            k += k and -k
        }
    }

    /**
     * Returns sum(l, r)
     * @param l left index
     * @param r right index
     */
    fun sum(l: Int, r: Int): Int {
        return sum(r) - sum(l - 1)
    }
}
