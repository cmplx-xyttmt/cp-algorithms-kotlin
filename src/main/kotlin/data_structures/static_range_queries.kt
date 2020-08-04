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
