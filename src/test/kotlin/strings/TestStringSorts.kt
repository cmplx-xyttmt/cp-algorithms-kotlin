package strings

import org.junit.Test
import kotlin.test.assertEquals

class TestStringSorts {

    @Test fun testKeyBasedIndexing() {
        val toSort = listOf(5, 3, 4, 1, 8, 12, 7, 3, 7, 8, 6, 9, 31, 60, 3)
        assertEquals(toSort.sorted(), keyIndexedCounting(toSort, toSort.max() ?: 0))
    }

    @Test fun testLsdSort() {
        val toSort = listOf("4PGC938", "2IYE230", "3CIO720", "1ICK750", "1OHV845", "4JZY524", "1ICK750",
            "3CIO720", "1OHV845", "1OHV845", "2RLA629", "2RLA629", "3ATW723")
        assertEquals(toSort.sorted(), lsdSort(toSort.toMutableList(), 7))
    }
}