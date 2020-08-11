package data_structures

import kotlin.math.max
import kotlin.math.min

class SegmentTree(private val array: IntArray, private val function: (Int, Int) -> Int, private val identity: Int) {
    val n = array.size
    private val tree = IntArray(4 * n)

    init {
        build(1, 0, n - 1)
    }

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

    private fun rangeQuery(v: Int, tl: Int, tr: Int, l: Int, r: Int): Int {
        if (l > r) return identity
        if (l == tl && r == tr) return tree[v]
        val tm = (tl + tr) / 2
        return function(
            rangeQuery(v * 2, tl, tm, l, min(r, tm)),
            rangeQuery(v * 2 + 1, tm + 1, tr, max(l, tm + 1), r)
        )
    }

    private fun update(v: Int, tl: Int, tr: Int, pos: Int, newVal: Int) {
        if (tl == tr) tree[v] = newVal
        else {
            val tm = (tl + tr)/2
            if (pos <= tm) update(v * 2, tl, tm, pos, newVal)
            else update(v * 2 + 1, tm + 1, tr, pos, newVal)
            tree[v] = function(tree[v * 2], tree[v * 2 + 1])
        }
    }

    fun rangeQuery(l: Int, r: Int): Int {
        return rangeQuery(1, 0, n - 1, l, r)
    }

    fun update(pos: Int, newVal: Int) {
        update(1, 0, n - 1, pos, newVal)
    }
}
