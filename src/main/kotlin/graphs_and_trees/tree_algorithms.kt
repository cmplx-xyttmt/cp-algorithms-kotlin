package graphs_and_trees
import java.util.*

/**
 * Represents a node in the tree
 * @param id id/number of the node
 * @param adj list of adjacent nodes to this node
 * @param dist distance to root (used here as an example for how to augment a graph/tree)
 */
data class Node(val id: Int, var adj: MutableList<Int>, var dist: Int)

/**
 * Represents a graph
 * @param size the number of nodes in the graph
 */
class Graph(size: Int) {
    val nodes = Array(size) { Node(it, mutableListOf(), 0) }

    fun addEdge(u: Int, v: Int) {
        nodes[u].adj.add(v)
        nodes[v].adj.add(u)
    }
}

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

    fun bfs(from: Node) {
        for (node in tree.nodes) node.dist = -1
        from.dist = 0
        val queue = ArrayDeque<Node>()
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
