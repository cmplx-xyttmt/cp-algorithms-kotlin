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
}