package data_structures

import org.junit.Test
import kotlin.test.assertEquals


class TestBinaryIndexedTree {

    @Test
    fun testBIT() {
        val array = mutableListOf(0, 13, 34, 17, 69, 31, 71, 22, 55, 82, 47, 85, 45, 51, 46, 73, 57, 17, 28, 50)

        fun sumBruteForce(l: Int, r: Int): Int {
            return array.subList(l, r + 1).sum()
        }

        val bit = BinaryIndexedTree(array.toIntArray())

        val queries = listOf(
            listOf(0, 1, array.size - 1),
            listOf(1, 13, 14),
            listOf(0, 13, 15),
            listOf(0, 6, 15),
            listOf(1, 9, 8),
            listOf(1, 6, 14),
            listOf(1, 9,62),
            listOf(1, 8, 73),
            listOf(1, 1, 18),
            listOf(0, 8, 17),
            listOf(1, 19, 48),
            listOf(0, 18, 19)
        )

        queries.forEach { query ->
            val l = query[1]
            val rx = query[2]
            if (query[0] == 0) assertEquals(sumBruteForce(l, rx), bit.sum(l, rx))
            else {
                array[l] += rx
                bit.add(l, rx)
            }
        }
    }
}