package graphs_and_trees
import java.util.*

/**
 * @param treeString a string representing the tree in the format:
 *                                                      n
 *                                                      x1 y1
 *                                                      x2 y2
 *                                                      .
 *                                                      .
 *                                                      .
 *                                                      x_(n - 1) y_(n - 1)
 * @return the diameter of the tree
 */
fun treeDiameter(treeString: String): Int {
    val tree = readGraph(treeString)

    fun bfs(from: TreeNode) {
        for (node in tree.nodes) node.dist = -1
        from.dist = 0
        val queue = ArrayDeque<TreeNode>()
        queue.add(from)
        while (queue.isNotEmpty()) {
            val next = queue.remove()
            for (node in next.adj) {
                if (tree.nodes[node].dist == -1) {
                    tree.nodes[node].dist = next.dist + 1
                    queue.add(tree.nodes[node])
                }
            }
        }
    }
    bfs(tree.nodes[0])
    val farthestNode = tree.nodes.maxBy { it.dist }!!
    bfs(farthestNode)
    return tree.nodes.maxBy { it.dist }!!.dist
}
