package strings

import org.junit.Test
import kotlin.test.assertEquals

class TestStringSorts {

    @Test fun testKeyBasedIndexing() {
        val toSort = listOf(5, 3, 4, 1, 8, 12, 7, 3, 7, 8, 6, 9, 31, 60, 3)
        assertEquals(toSort.sorted(), keyIndexedCounting(toSort, toSort.max() ?: 0))
    }
}