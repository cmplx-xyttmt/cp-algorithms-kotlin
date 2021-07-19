package graphs_and_trees

import org.junit.Test
import kotlin.test.assertEquals

class TestTreeAlgorithms {

    @Test
    fun testTreeDiameter() {
        val testCases = mapOf(
            "5\n4 2\n1 4\n5 4\n3 4\n" to 2,
            "6\n1 2\n2 3\n2 4\n4 5\n4 6\n" to 3,
            "1\n" to 0,
            "11\n1 2\n2 3\n2 4\n4 5\n1 6\n6 7\n6 8\n8 9\n9 10\n10 11" to 8
        )

        testCases.forEach { (tree, diameter) ->
            assertEquals(diameter, treeDiameter(tree))
        }
    }

    @Test
    fun testKthAncestor() {
        val tree = Tree(10)
        val parents = mutableListOf(1, 2, 1, 3, 3, 3, 1, 3, 5)
        for (i in parents.indices) tree.addParent(parents[i], i + 2)
        tree.preprocess()

        val queriesToAns = mutableMapOf(
            Pair(1, 1) to -1,
            Pair(2, 1) to 1,
            Pair(8, 1) to 1,
            Pair(8, 1) to 1,
            Pair(3, 1) to 2,
            Pair(5, 3) to 1,
            Pair(5, 1) to 3,
            Pair(8, 1) to 1,
            Pair(8, 1) to 1,
            Pair(6, 3) to 1
        )

        queriesToAns.forEach { (query, ans) ->
            assertEquals(ans, tree.kthAncestor(query.first, query.second))
        }
    }
}
