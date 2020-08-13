package data_structures

import kotlin.math.max
import kotlin.math.min

/**
 * A segment tree supports range queries and updates on an array.
 *
 * @param array the original array
 * @param function the function for which to calculate range queries. This function should be associative i.e (a x b) x c = a x (b x c)
 * @param identity identity I of the function i.e for all a, a x I = a e.g for addition, identity is 0
 */
class SegmentTree(private val array: IntArray, private val function: (Int, Int) -> Int, private val identity: Int) {
    val n = array.size
    private val tree = IntArray(4 * n)

    init {
        build(1, 0, n - 1)
    }

    /**
     * Builds the segment tree
     * @param v the current node of the tree
     * @param tl the left index represented by the current node
     * @param tr the right index represented by the current node
     */
    private fun build(v: Int, tl: Int, tr: Int) {
        if (tl == tr) {
            tree[v] = array[tl]
        } else {
            val tm = (tl + tr) / 2
            build(v * 2, tl, tm)
            build(v * 2 + 1, tm + 1, tr)
            tree[v] = function(tree[v * 2], tree[v * 2 + 1])
        }
    }

    /**
     * Process a range query from the index l to index r
     * @param v the current node of the tree
     * @param tl the left index represented by the current node
     * @param tr the right index represented by the current node
     * @param l left index
     * @param r right index
     */
    private fun rangeQuery(v: Int, tl: Int, tr: Int, l: Int, r: Int): Int {
        if (l > r) return identity
        if (l == tl && r == tr) return tree[v]
        val tm = (tl + tr) / 2
        return function(
            rangeQuery(v * 2, tl, tm, l, min(r, tm)),
            rangeQuery(v * 2 + 1, tm + 1, tr, max(l, tm + 1), r)
        )
    }

    /**
     * Update the value at position pos to newVal
     * @param v the current node of the tree
     * @param tl the left index represented by the current node
     * @param tr the right index represented by the current node
     * @param pos position
     * @param newVal the new value
     */
    private fun update(v: Int, tl: Int, tr: Int, pos: Int, newVal: Int) {
        if (tl == tr) tree[v] = newVal
        else {
            val tm = (tl + tr) / 2
            if (pos <= tm) update(v * 2, tl, tm, pos, newVal)
            else update(v * 2 + 1, tm + 1, tr, pos, newVal)
            tree[v] = function(tree[v * 2], tree[v * 2 + 1])
        }
    }

    /**
     * Process range query from l to r
     * @param l left index
     * @param r right index
     */
    fun rangeQuery(l: Int, r: Int): Int {
        return rangeQuery(1, 0, n - 1, l, r)
    }

    /**
     * Update value at position
     * @param pos the position
     * @param newVal the new value
     */
    fun update(pos: Int, newVal: Int) {
        update(1, 0, n - 1, pos, newVal)
    }
}
