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
}