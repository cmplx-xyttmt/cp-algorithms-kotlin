package recursion

import org.junit.Test
import kotlin.test.assertEquals

class TestUsefulFunctions {

    @Test fun testSubsets() {
        assertEquals(listOf(listOf()), subsets(0))
        assertEquals(listOf(listOf(), listOf(1)), subsets(1))
        assertEquals(listOf(listOf(), listOf(1), listOf(2), listOf(1, 2)), subsets(2))
        assertEquals(listOf(listOf(), listOf(1), listOf(2), listOf(3), listOf(1, 2),
            listOf(1, 3), listOf(2, 3), listOf(1, 2, 3)), subsets(3))
    }

    @Test fun testPermutations() {
        assertEquals(listOf(listOf()), permutations(0))
        assertEquals(listOf(listOf(1)), permutations(1))
        assertEquals(listOf(listOf(1, 2), listOf(2, 1)), permutations(2))
        assertEquals(listOf(listOf(1, 2, 3), listOf(1, 3, 2), listOf(2, 1, 3), listOf(2, 3, 1),
            listOf(3, 1, 2), listOf(3, 2, 1)), permutations(3))
    }

    @Test fun testNextPermutation() {
        var perm = listOf(1, 2, 3, 4)
        val permutations = permutations(4).iterator()
        while (perm.isNotEmpty()) {
            assertEquals(permutations.next(), perm)
            perm = nextPermutation(perm)
        }
    }
}