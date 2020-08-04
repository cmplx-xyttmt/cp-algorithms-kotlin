package data_structures

import kotlin.math.ceil
import kotlin.math.log2

/**
 * Build the 2d prefix sum grid where prefixSum[ a ][ b ] = sum(sum([grid[ i ][ j ] for j in range(0, b + 1)]) for i in range(0, a + 1))
 * @param grid the 2d grid of numbers
 * @return the 2d prefix sum
 */
fun buildPrefixSumGrid(grid: List<List<Int>>): Array<IntArray> {
    val prefixSum = Array(grid.size) { IntArray(grid[it].size) { 0 } }
    for (i in grid.indices) {
        for (j in grid[i].indices) {
            prefixSum[i][j] += grid[i][j]
            if (i - 1 >= 0) prefixSum[i][j] += prefixSum[i - 1][j]
            if (j - 1 >= 0) prefixSum[i][j] += prefixSum[i][j - 1]
            if (i - 1 >= 0 && j - 1 >= 0) prefixSum[i][j] -= prefixSum[i - 1][j - 1]
        }
    }
    return prefixSum
}

/**
 * Returns the sum of the numbers in the rectangle bounded by the indices (x, y) and (a, b) where x <= a and y <= b
 * i.e the value sum(sum([grid[ i ][ j ] for j in range(y, b + 1)]) for i in range(x, a + 1))
 *
 * @param x first row index
 * @param y first column index
 * @param a second row index
 * @param b second column index
 * @return the value described above
 */
fun processPrefixSumQuery(x: Int, y: Int, a: Int, b: Int, prefixSum: Array<IntArray>): Int {
    val whole = prefixSum[a][b]
    val left = if (y - 1 >= 0) prefixSum[a][y - 1] else 0
    val above = if (x - 1 >= 0) prefixSum[x - 1][b] else 0
    val diagonal = if (x - 1 >= 0 && y - 1 >= 0) prefixSum[x - 1][y - 1] else 0
    return whole - left - above + diagonal
}

/**
 * Builds a sparse table from an array which can answer range queries of the type function on a static array
 *
 * @param array the array of values
 * @param function the function of the queries. Note: This has to be an idempotent function (see here: https://en.wikipedia.org/wiki/Idempotence)
 * @return the sparse table
 */
fun buildSparseTable(array: List<Int>, function: (Int, Int) -> Int): Array<IntArray>  {
    val n = array.size
    val k = ceil(log2(n.toDouble())).toInt()

    val sparseTable = Array(n) { IntArray(k + 1) }
    for (i in 0 until n) sparseTable[i][0] = function(array[i], array[i])

    for (j in 1..k) {
        var i = 0
        while (i + (1 shl j) <= n) {
            sparseTable[i][j] = function(sparseTable[i][j - 1], sparseTable[i + (1 shl (j - 1))][j - 1])
            i++
        }
    }

    return sparseTable
}

/**
 * Computes floor(log2(i)) for i in 1..n
 *
 * @param n
 */
fun preComputeLogarithms(n: Int): IntArray {
    val log = IntArray(n + 1)
    log[1] = 0
    for (i in 2..n) {
        log[i] = log[i/2] + 1
    }
    return log
}

/**
 * Returns the value of function(array[ l ],...,array[ r ]) for the given function
 * @param l left index
 * @param r right index
 * @param sparseTable the sparse table of the array
 * @param function function of the queries (e.g max, min or gcd). Note: must be idempotent
 * @return returns the value of the function for the given indices
 */
fun processSparseTableQuery(l: Int, r: Int, sparseTable: Array<IntArray>, function: (Int, Int) -> Int, logs: IntArray): Int {
    val j = logs[r - l + 1]
    return function(sparseTable[l][j], sparseTable[r - (1 shl j) + 1][j])
}
