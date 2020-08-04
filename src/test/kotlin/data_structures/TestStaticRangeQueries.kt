package data_structures

import org.junit.Test
import kotlin.math.max
import kotlin.test.assertEquals

class TestStaticRangeQueries {

    @Test
    fun testTwoDPrefixSum() {
        val grid = listOf(
            listOf(13, 34, 17, 69, 31, 71, 22, 55, 82, 47, 85, 45, 51, 46, 73, 57, 17, 28, 50),
            listOf(35, 50, 72, 87, 99, 59, 70, 86, 11, 91, 66, 79, 43, 82, 65, 75, 77, 35, 53),
            listOf(81, 94, 14, 65, 64, 24, 12, 98, 19, 11, 20, 43, 13, 14, 50, 68, 47, 79, 77),
            listOf(24, 96, 29, 96, 92, 85, 24, 49, 88, 32, 60, 13, 28, 13, 88, 64, 34, 14, 87),
            listOf(19, 88, 28, 50, 55, 89, 92, 43, 89, 48, 33, 45, 63, 90, 54, 41, 93, 47, 24),
            listOf(32, 24, 72, 41, 61, 63, 46, 95, 54, 14, 75, 16, 91, 83, 89, 38, 87, 87, 47),
            listOf(63, 37, 76, 39, 29, 99, 45, 26, 44, 86, 82, 64, 42, 17, 84, 72, 81, 70, 51),
            listOf(76, 33, 37, 32, 45, 69, 12, 83, 24, 46, 95, 21, 16, 43, 28, 18, 11, 34, 82),
            listOf(81, 12, 46, 42, 54, 45, 92, 14, 95, 36, 23, 34, 62, 88, 39, 28, 29, 61, 46),
            listOf(28, 99, 13, 73, 95, 46, 67, 15, 36, 52, 48, 82, 82, 78, 74, 20, 92, 17, 23),
            listOf(13, 25, 39, 58, 13, 58, 88, 68, 99, 43, 53, 27, 13, 98, 87, 91, 54, 62, 50),
            listOf(60, 26, 51, 22, 41, 91, 61, 33, 18, 57, 38, 88, 84, 22, 29, 55, 52, 33, 51),
            listOf(60, 89, 94, 75, 22, 15, 21, 26, 56, 60, 69, 29, 17, 31, 25, 44, 95, 12, 13),
            listOf(55, 83, 51, 74, 75, 65, 25, 71, 53, 85, 90, 81, 11, 70, 35, 75, 40, 43, 24),
            listOf(79, 43, 48, 44, 96, 87, 78, 32, 33, 27, 48, 79, 67, 40, 25, 25, 20, 95, 30)
        )

        fun bruteForce(x: Int, y: Int, a: Int, b: Int): Int {
            var total = 0
            for (i in x..a) {
                total += grid[i].subList(y, b + 1).sum()
            }
            return total
        }

        val queries = mapOf(
            listOf(1, 1, 1, 1) to bruteForce(1, 1, 1, 1),
            listOf(0, 0, 0, 0) to bruteForce(0, 0, 0, 0),
            listOf(0, 0, grid.size - 1, grid[0].size - 1) to bruteForce(0, 0, grid.size - 1, grid[0].size - 1),
            listOf(3, 13, 13, 18) to bruteForce(3, 13, 13, 18),
            listOf(14, 15, 14, 16) to bruteForce(14, 15, 14, 16),
            listOf(6, 6, 7, 6) to bruteForce(6, 6, 7, 6),
            listOf(6, 5, 6, 10) to bruteForce(6, 5, 6, 10),
            listOf(2, 13, 12, 16) to bruteForce(2, 13, 12, 16),
            listOf(3, 18, 10, 18) to bruteForce(3, 18, 10, 18),
            listOf(5, 16, 12, 17) to bruteForce(5, 16, 12, 17),
            listOf(9, 18, 10, 18) to bruteForce(9, 18, 10, 18)
        )

        val prefixSum = buildPrefixSumGrid(grid)

        queries.forEach { (params, answer) ->
            val (x, y, a, b) = params
            assertEquals(answer, processPrefixSumQuery(x, y, a, b, prefixSum))
        }
    }

    @Test
    fun testSparseTableMax() {
        val function = { a: Int, b: Int -> max(a, b) }
        val array = listOf(13, 34, 17, 69, 31, 71, 22, 55, 82, 47, 85, 45, 51, 46, 73, 57, 17, 28, 50)
        val sparseTable = buildSparseTable(array, function)
        val logs = preComputeLogarithms(array.size)

        fun bruteForce(l: Int, r: Int): Int {
            return array.subList(l, r + 1).max()!!
        }

        val queries = mapOf(
            Pair(1, 1) to bruteForce(1, 1),
            Pair(array.size - 1, array.size - 1) to bruteForce(array.size - 1, array.size - 1),
            Pair(14, 15) to bruteForce(14, 15),
            Pair(2, 12) to bruteForce(2, 12),
            Pair(8, 13) to bruteForce(8, 13),
            Pair(2, 16) to bruteForce(2, 16),
            Pair(12, 14) to bruteForce(12, 14),
            Pair(8, 18) to bruteForce(8, 18)
        )

        queries.forEach { (pair, answer) ->
            val (l, r) = pair
            assertEquals(answer, processSparseTableQuery(l, r, sparseTable, function, logs))
        }
    }
}
