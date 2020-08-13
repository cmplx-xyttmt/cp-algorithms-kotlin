package data_structures

import math.gcd
import org.junit.Test
import kotlin.math.max
import kotlin.math.min
import kotlin.test.assertEquals

class TestSegmentTree {

    @Test
    fun testSegmentTree() {
        val sumFunction = { a: Int, b: Int -> a + b }
        val maxFunction = { a: Int, b: Int -> max(a, b) }
        val minFunction = { a: Int, b: Int -> min(a, b) }
        val gcdFunction = { a: Int, b: Int -> gcd(b.toLong(), a.toLong()).toInt() }
        val xorFunction = { a: Int, b: Int -> a xor b }
        val orFunction = { a: Int, b: Int -> a or b }
        val andFunction = { a: Int, b: Int -> a and b }

        val array = mutableListOf(13, 34, 17, 69, 31, 71, 22, 55, 82, 47, 85, 45, 51, 46, 73, 57, 17, 28, 50, 47)

        val queries = listOf(
            listOf(0, 1, array.size - 1),
            listOf(0, 6, 7),
            listOf(1, 13, 14),
            listOf(0, 13, 15),
            listOf(0, 6, 15),
            listOf(1, 9, 8),
            listOf(1, 6, 14),
            listOf(1, 9, 62),
            listOf(1, 8, 73),
            listOf(1, 1, 18),
            listOf(0, 8, 17),
            listOf(1, 19, 48),
            listOf(0, 18, 19)
        )

        fun runTest(function: (Int, Int) -> Int, identity: Int) {
            val arr = array.toIntArray()
            val segmentTree = SegmentTree(arr, function, identity)

            fun bruteForce(l: Int, r: Int): Int {
                return arr.toList().subList(l, r + 1).reduce { acc, num -> function(acc, num) }
            }

            queries.forEach { (type, l, rx) ->
                val functionName = when (function) {
                    sumFunction -> "Sum"
                    maxFunction -> "Max"
                    minFunction -> "Min"
                    xorFunction -> "XOR"
                    orFunction -> "OR"
                    andFunction -> "AND"
                    else -> "GCD"
                }
                if (type == 0) {
                    println("$functionName[$l, $rx] = ${segmentTree.rangeQuery(l, rx)}")
                    assertEquals(bruteForce(l, rx), segmentTree.rangeQuery(l, rx))
                } else {
                    arr[l] = rx
                    segmentTree.update(l, rx)
                }
            }
        }

        runTest(sumFunction, 0)
        runTest(maxFunction, Int.MIN_VALUE)
        runTest(minFunction, Int.MAX_VALUE)
        runTest(gcdFunction, 0)
        runTest(xorFunction, 0)
        runTest(orFunction, 0)
        runTest(andFunction, Int.MAX_VALUE)
    }
}
