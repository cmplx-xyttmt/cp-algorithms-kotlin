package graphs_and_trees

import kotlin.math.log2

/**
 * Represents a node in the tree
 * @param id id/number of the node
 * @param adj list of adjacent nodes to this node
 */
class TreeNode(private val id: Int, var adj: MutableList<Int>) {
    // dist distance to root (used here as an example for how to augment a graph/tree)
    var dist: Int = 0
}

/**
 * Represents a tree.
 * Usage: If using a tree the pow2Ancestors:
 * - If tree is not rooted, root it and add each child node's parent
 * - Make a call to preprocess after adding parents
 * @param size the number of nodes in the tree
 */
class Tree(val size: Int) {
    val nodes = Array(size + 1) { TreeNode(it, mutableListOf()) }

    // pow2Ancestors[i][x] is the ancestor of x at a distance of 2^i from x
    private val pow2Ancestors = MutableList(log2(size.toDouble()).toInt() + 2) { MutableList(size + 1) { 0 } }

    fun addEdge(u: Int, v: Int) {
        nodes[u].adj.add(v)
        nodes[v].adj.add(u)
    }

    // Useful if the tree is a rooted tree
    fun addParent(parent: Int, child: Int) {
        pow2Ancestors[0][child] = parent
    }


    /**
     * Calculates the ancestors of nodes at distance of powers of 2
     * Assumes root of the tree is node 1
     */
    fun preprocess() {
        for (i in 1 until pow2Ancestors.size) {
            for (x in 1..size) pow2Ancestors[i][x] = pow2Ancestors[i - 1][pow2Ancestors[i - 1][x]]
        }
    }

    /**
     * Returns the kth ancestor of node x
     * Note: Need to change implementation if tree is not rooted at 1
     */
    fun kthAncestor(x: Int, kk: Int): Int {
        var k = kk
        var i = 0
        var ans = x
        while (k > 0) {
            if (k % 2 == 1) ans = pow2Ancestors[i][ans]
            k = k.shr(1)
            i++
        }
        if (ans == 0) return -1
        return ans
    }
}
