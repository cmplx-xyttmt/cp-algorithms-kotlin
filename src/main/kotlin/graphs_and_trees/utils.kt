package graphs_and_trees

/**
 * @param graphString a string representing the graph in the format:
 *                                                      n m
 *                                                      x1 y1
 *                                                      x2 y2
 *                                                      .
 *                                                      .
 *                                                      .
 *                                                      x_(m - 1) y_(m - 1)
 * @return the diameter of the tree
 */
fun readGraph(graphString: String): Graph {
    val lines = graphString.split("\n")
    val params = lines[0].split(" ").map { it.toInt() }
    val n = params[0]
    var m = n - 1
    if (params.size > 1) m = params[1]

    val graph = Graph(n)
    for (i in 1..m) {
        val (u, v) = lines[i].split(" ").map { it.toInt() }
        graph.addEdge(u - 1, v - 1)
    }

    return graph
}
